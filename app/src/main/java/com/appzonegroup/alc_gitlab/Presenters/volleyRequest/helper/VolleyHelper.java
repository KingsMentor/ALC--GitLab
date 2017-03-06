package com.appzonegroup.alc_gitlab.Presenters.volleyRequest.helper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Presenters.volleyRequest.request.GitUserRequest;

/**
 * Created by zone2 on 3/6/17.
 */

public class VolleyHelper {
    private final String URL = "https://api.github.com/search/users?q=language:java+location:Lagos";

    public Request<GitUserRequestData> requestJavaDevelopersInLagos(
            Response.Listener<GitUserRequestData> listener, Response.ErrorListener errorListener) {
        return new GitUserRequest(URL, Request.Method.GET, GitUser.class, null, listener, errorListener);

    }
}
