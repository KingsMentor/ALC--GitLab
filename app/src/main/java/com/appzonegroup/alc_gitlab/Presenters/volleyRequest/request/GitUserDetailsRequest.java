package com.appzonegroup.alc_gitlab.Presenters.volleyRequest.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.appzonegroup.alc_gitlab.Models.GitUserDetails;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * Created by zone2 on 3/6/17.
 */

public class GitUserDetailsRequest extends Request<GitUserDetails> {

    private final Gson gson = new Gson();
    private final Class<GitUserDetails> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<GitUserDetails> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GitUserDetailsRequest(String url, int method, Class<GitUserDetails> clazz, Map<String, String> headers,
                                 Response.Listener<GitUserDetails> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(GitUserDetails response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<GitUserDetails> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            try {
                JSONObject responseObject = new JSONObject(json);

                GitUserDetails gitUser = gson.fromJson(responseObject.toString(), clazz);

                return Response.success(gitUser,
                        HttpHeaderParser.parseCacheHeaders(response));
            } catch (JSONException e) {
                return Response.error(new ParseError(e));
            }

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
