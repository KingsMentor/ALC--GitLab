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

    public void startLoadingData() {
        Request<GitUserRequestData> request = new VolleyHelper().requestJavaDevelopersInLagos(new Response.Listener<GitUserRequestData>() {
            @Override
            public void onResponse(GitUserRequestData response) {
                if (gitUserListAdapter == null)
                    gitUserListAdapter = new GitUserListAdapter();
                ActivityNotifier.getInstance().notifyAdapterUpdate(gitUserListAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ActivityNotifier.getInstance().failedDataRefresh();
            }
        });
        GitApplication.getInstance().addToRequestQueue(request, DataLoaderController.class.getSimpleName());
    }


}
