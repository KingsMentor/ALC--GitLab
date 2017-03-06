package com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zone2 on 3/6/17.
 */

public abstract class ActivityNotifier extends AppCompatActivity {

    public static ActivityNotifier activityNotifier;

    public static synchronized ActivityNotifier getInstance() {
        return activityNotifier;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (activityNotifier == null)
            activityNotifier = this;
    }

    public void registerReceiver(String intentFilter) {

    }


    public void unregisterReceiver(String intentFilter) {

    }


    protected abstract void notifyUpdate(Intent intent);

}
