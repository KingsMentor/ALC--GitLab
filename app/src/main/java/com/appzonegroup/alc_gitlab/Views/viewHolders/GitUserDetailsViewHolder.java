package com.appzonegroup.alc_gitlab.Views.viewHolders;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.appzonegroup.alc_gitlab.R;

/**
 * Created by zone2 on 3/9/17.
 */

public class GitUserDetailsViewHolder extends RecyclerView.ViewHolder {
    private AppCompatTextView title, details;
    private Context context;

    public GitUserDetailsViewHolder(View itemView) {
        super(itemView);
        title = (AppCompatTextView) itemView.findViewById(R.id.title);
        details = (AppCompatTextView) itemView.findViewById(R.id.description);

    }

    public Context getContext() {
        return this.context;
    }

    public AppCompatTextView getTitle() {
        return this.title;
    }

    public AppCompatTextView getDetails() {
        return this.details;
    }
}
