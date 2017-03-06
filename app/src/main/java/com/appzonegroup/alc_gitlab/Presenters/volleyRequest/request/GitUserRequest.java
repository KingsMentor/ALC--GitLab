package com.appzonegroup.alc_gitlab.Presenters.volleyRequest.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * Created by zone2 on 3/6/17.
 */

public class GitUserRequest extends Request<GitUserRequestData> {

    private final Gson gson = new Gson();
    private final Class<GitUser> clazz;
    private final Map<String, String> headers;
    private final Response.Listener<GitUserRequestData> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GitUserRequest(String url, int method, Class<GitUser> clazz, Map<String, String> headers,
                          Response.Listener<GitUserRequestData> listener, Response.ErrorListener errorListener) {
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
    protected void deliverResponse(GitUserRequestData response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<GitUserRequestData> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            try {
                JSONObject responseObject = new JSONObject(json);
                int totalCount = responseObject.getInt("total_count");
                boolean incomplete = responseObject.getBoolean("incomplete_results");
                GitUserRequestData gitUserRequestData = new GitUserRequestData(totalCount, incomplete);
                JSONArray responseArray = responseObject.getJSONArray("items");
                for (int responseIndex = 0; responseIndex < responseArray.length(); responseIndex++) {
                    GitUser gitUser = gson.fromJson(responseArray.getString(responseIndex), clazz);
                    gitUserRequestData.addGitUser(gitUser);
                }
                return Response.success(gitUserRequestData,
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
