package com.appzonegroup.alc_gitlab.Views.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserAdapterViewHolder;
import com.bumptech.glide.Glide;

/**
 * Created by zone2 on 3/6/17.
 */

public class GitUserListAdapter extends RecyclerView.Adapter<GitUserAdapterViewHolder> {

    private GitUserRequestData mGitUserRequestData;

    public GitUserListAdapter(GitUserRequestData gitUserRequestData) {
        this.mGitUserRequestData = gitUserRequestData;
    }

    @Override
    public GitUserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GitUserAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.git_user_row, parent, false));
    }

    @Override
    public void onBindViewHolder(GitUserAdapterViewHolder holder, int position) {
        holder.gitUserProfileNameField.setText(getName(position));
        Glide.with(holder.mContext).load(Uri.parse(getImageUrl(position))).into(holder.gitUserProfileImageView);
    }

    @Override
    public int getItemCount() {
        return mGitUserRequestData.getGitUsers().size();
    }

    private String getName(int position) {
        return mGitUserRequestData.getGitUsers().get(position).getLogin();
    }

    private String getImageUrl(int position) {
        return mGitUserRequestData.getGitUsers().get(position).getAvatarUrl();
    }

    public void updateData(GitUserRequestData gitUserRequestData) {
        mGitUserRequestData.getGitUsers().addAll(gitUserRequestData.getGitUsers());
        notifyDataSetChanged();
    }
}
