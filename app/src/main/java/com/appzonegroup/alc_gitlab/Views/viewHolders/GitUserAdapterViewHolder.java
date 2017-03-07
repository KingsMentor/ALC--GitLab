package com.appzonegroup.alc_gitlab.Views.viewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.appzonegroup.alc_gitlab.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zone2 on 3/6/17.
 */

public class GitUserAdapterViewHolder extends RecyclerView.ViewHolder {
    public CircleImageView gitUserProfileImageView;
    public TextView gitUserProfileNameField;
    public Context mContext;

    public GitUserAdapterViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        gitUserProfileImageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
        gitUserProfileNameField = (TextView) itemView.findViewById(R.id.git_user_profile_name);
    }
}
