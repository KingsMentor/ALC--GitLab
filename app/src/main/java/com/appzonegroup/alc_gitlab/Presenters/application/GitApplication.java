package com.appzonegroup.alc_gitlab.Presenters.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.appzonegroup.alc_gitlab.Presenters.loader.DataLoaderController;
import com.appzonegroup.alc_gitlab.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by zone2 on 3/5/17.
 */

public class GitApplication extends Application {


    private RequestQueue mRequestQueue;

    public static final String TAG = GitApplication.class
            .getSimpleName();

    private DataLoaderController dataLoaderController;

    static GitApplication gitApplication;

    public synchronized static GitApplication getInstance() {
        return gitApplication;
    }


    public DataLoaderController getDataLoaderController() {
        return this.dataLoaderController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gitApplication = this;
        dataLoaderController = new DataLoaderController();
        dataLoaderController.startLoadingData();
        defineCalligraphy();

    }

    private void defineCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source_code.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
