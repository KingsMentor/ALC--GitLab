package com.appzonegroup.alc_gitlab.Presenters.loader;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserDetails;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.ActivityNotifier;
import com.appzonegroup.alc_gitlab.Presenters.volleyRequest.helper.VolleyHelper;
import com.appzonegroup.alc_gitlab.Presenters.volleyRequest.request.GitUserDetailsRequest;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;
import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserAdapterViewHolder;

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
                        gitUserListAdapter = new GitUserListAdapter(response) {
                            @Override
                            public void onItemSelected(GitUserAdapterViewHolder holder, GitUser gitUser, int position) {
                                ActivityNotifier.getInstance().onItemSelected(holder, gitUser, position);
                            }
                        };
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

    public void retrieveUserDetails(GitUser gitUser, Response.Listener<GitUserDetails> listener) {
        Request request = new GitUserDetailsRequest(gitUser.getUrl(), Request.Method.GET, GitUserDetails.class, null, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        GitApplication.getInstance().addToRequestQueue(request, gitUser.getUrl());
    }

}
