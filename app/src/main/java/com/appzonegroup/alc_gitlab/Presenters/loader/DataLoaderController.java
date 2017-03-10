package com.appzonegroup.alc_gitlab.Presenters.loader;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserDetails;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Models.enums.Sort;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.UpdateNotifier;
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
    private boolean sortChanged = true;
    private Sort currentSort = Sort.FOLLOWERS;


    public void startLoadingData(Sort sortBy) {
        if (currentSort != sortBy) {
            page = 1;
            currentSort = sortBy;
            sortChanged = true;
        } else {
            sortChanged = false;
        }
        if (!isLoading) {
            isLoading = true;
            Request<GitUserRequestData> request = new VolleyHelper().requestJavaDevelopersInLagos(sortBy, page, new Response.Listener<GitUserRequestData>() {
                @Override
                public void onResponse(GitUserRequestData response) {
                    isLoading = false;
                    page++;
                    if (gitUserListAdapter == null || sortChanged) {
                        gitUserListAdapter = new GitUserListAdapter(response) {
                            @Override
                            public void onItemSelected(GitUserAdapterViewHolder holder, GitUser gitUser, int position) {
                                UpdateNotifier.getInstance().onItemSelected(holder, gitUser, position);
                            }
                        };
                        if (UpdateNotifier.getInstance() != null)
                            UpdateNotifier.getInstance().notifyAdapterCreated(gitUserListAdapter);
                    } else {
                        gitUserListAdapter.updateData(response);
                        if (UpdateNotifier.getInstance() != null)
                            UpdateNotifier.getInstance().notifyAdapterUpdated(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    isLoading = false;
                    if (UpdateNotifier.getInstance() != null)
                        UpdateNotifier.getInstance().failedDataRefresh();
                }
            });
            GitApplication.getInstance().addToRequestQueue(request, DataLoaderController.class.getSimpleName());
        }
    }

    public void retrieveUserDetails(GitUser gitUser, Response.Listener<GitUserDetails> listener, Response.ErrorListener errorListener) {
        Request request = new GitUserDetailsRequest(gitUser.getUrl(), Request.Method.GET, GitUserDetails.class, null, listener, errorListener);
        GitApplication.getInstance().addToRequestQueue(request, gitUser.getUrl());
    }

}
