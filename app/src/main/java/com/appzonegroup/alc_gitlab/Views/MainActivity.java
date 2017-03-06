package com.appzonegroup.alc_gitlab.Views;

import android.os.Bundle;
import android.util.Log;

import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.ActivityNotifier;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;

public class MainActivity extends ActivityNotifier {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void notifyAdapterUpdate(GitUserListAdapter adapter) {
        Log.e("success", "make update in activity");
    }

    @Override
    public void failedDataRefresh() {

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}
