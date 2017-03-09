package com.appzonegroup.alc_gitlab.Views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserDetailsViewHolder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zone2 on 3/9/17.
 */

public class GitUserDetailAdapter extends RecyclerView.Adapter<GitUserDetailsViewHolder> {

    private ArrayList<String> headers = new ArrayList<>(Arrays.asList(new String[]{"Profile Url", "Name", "Bio", "Blog", "Followers", "Following", "Public Repo"}));

    @Override
    public GitUserDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GitUserDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return headers.size();
    }
}
