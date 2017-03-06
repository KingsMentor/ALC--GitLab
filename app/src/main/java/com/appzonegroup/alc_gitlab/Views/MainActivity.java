package com.appzonegroup.alc_gitlab.Views;

import android.content.Intent;
import android.os.Bundle;

import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.ActivityNotifier;
import com.appzonegroup.alc_gitlab.R;

public class MainActivity extends ActivityNotifier {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver("");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver("");
    }

    @Override
    protected void notifyUpdate(Intent intent) {

    }
}
