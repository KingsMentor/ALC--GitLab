package com.appzonegroup.alc_gitlab.Views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appzonegroup.alc_gitlab.Models.GitUserDetails;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserDetailsViewHolder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zone2 on 3/9/17.
 */

public class GitUserDetailAdapter extends RecyclerView.Adapter<GitUserDetailsViewHolder> {

    private ArrayList<String> header = new ArrayList<>(Arrays.asList(new String[]{"Profile Url", "Name", "Bio", "Blog",
            "Followers", "Following", "Public Repo"}));

    private GitUserDetails mGitUserDetails;

    public GitUserDetailAdapter(GitUserDetails gitUserDetails) {
        mGitUserDetails = gitUserDetails;
    }

    @Override
    public GitUserDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GitUserDetailsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.details_row, parent, false));
    }

    @Override
    public void onBindViewHolder(GitUserDetailsViewHolder holder, int position) {
        holder.getTitle().setText(header.get(position));
        switch (header.get(position)) {
            case "Profile Url":
                holder.getDetails().setText(mGitUserDetails.getProfileUrl());
                break;
            case "Name":
                holder.getDetails().setText(mGitUserDetails.getName());
                break;
            case "Bio":
                holder.getDetails().setText(mGitUserDetails.getBio());
                break;
            case "Blog":
                holder.getDetails().setText(mGitUserDetails.getBlog());
                break;
            case "Followers":
                holder.getDetails().setText(String.valueOf(mGitUserDetails.getFollowers()));
                break;
            case "Following":
                holder.getDetails().setText(String.valueOf(mGitUserDetails.getFollowing()));
                break;
            default:
                holder.getDetails().setText(String.valueOf(mGitUserDetails.getFollowers()));
        }

    }

    @Override
    public int getItemCount() {
        return header.size();
    }
}
