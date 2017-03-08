package com.appzonegroup.alc_gitlab.Views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.ActivityNotifier;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.EnhanceRecyclerView;

public class MainActivity extends ActivityNotifier implements EnhanceRecyclerView.listenToScroll {

    EnhanceRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (EnhanceRecyclerView) findViewById(R.id.git_users);
        recyclerView.listen(this);
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

    @Override
    public void reachedEndOfList() {
        GitApplication.getInstance().getDataLoaderController().startLoadingData();
    }
}
