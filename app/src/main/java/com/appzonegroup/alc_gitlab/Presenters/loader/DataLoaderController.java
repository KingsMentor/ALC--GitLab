package com.appzonegroup.alc_gitlab.Presenters.loader;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.ActivityNotifier;
import com.appzonegroup.alc_gitlab.Presenters.volleyRequest.helper.VolleyHelper;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;

/**
 * Created by zone2 on 3/5/17.
 */

public class DataLoaderController {

    GitUserListAdapter gitUserListAdapter;
    private int page = 1;
    private boolean isLoading;

    public void startLoadingData() {
        if (!isLoading) {
            isLoading = true;
            Request<GitUserRequestData> request = new VolleyHelper().requestJavaDevelopersInLagos(page, new Response.Listener<GitUserRequestData>() {
                @Override
                public void onResponse(GitUserRequestData response) {
                    isLoading = false;
                    page++;
                    if (gitUserListAdapter == null) {
                        gitUserListAdapter = new GitUserListAdapter(response);
                        ActivityNotifier.getInstance().notifyAdapterCreated(gitUserListAdapter);
                    } else {
                        gitUserListAdapter.updateData(response);
                        ActivityNotifier.getInstance().notifyAdapterUpdated(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    isLoading = false;
                    ActivityNotifier.getInstance().failedDataRefresh();
                }
            });
            GitApplication.getInstance().addToRequestQueue(request, DataLoaderController.class.getSimpleName());
        }
    }

}
