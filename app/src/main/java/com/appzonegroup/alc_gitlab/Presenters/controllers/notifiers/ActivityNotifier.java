package com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;
import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserAdapterViewHolder;

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


    public abstract void notifyAdapterCreated(GitUserListAdapter adapter);

    public abstract void failedDataRefresh();

    public abstract void notifyAdapterUpdated(GitUserRequestData gitUserRequestData);

    public abstract void onItemSelected(GitUserAdapterViewHolder holder, GitUser gitUserRequestData, int position);

}
