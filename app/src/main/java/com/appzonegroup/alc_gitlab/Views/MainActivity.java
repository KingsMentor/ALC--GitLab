package com.appzonegroup.alc_gitlab.Views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.ActivityNotifier;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;

public class MainActivity extends ActivityNotifier {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.git_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

    }

    @Override
    public void notifyAdapterCreated(GitUserListAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void failedDataRefresh() {

    }

    @Override
    public void notifyAdapterUpdated(GitUserRequestData gitUserRequestData) {

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}
