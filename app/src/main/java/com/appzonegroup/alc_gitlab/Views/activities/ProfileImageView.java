package com.appzonegroup.alc_gitlab.Views.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileImageView extends AppCompatActivity {

    public static final String INTENT_DATA = "com.appzonegroup.alc_gitlab.Views.activities.ProfileImageView.INTENT_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile_image_view);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        AppCompatImageView gitProfileImageView = (AppCompatImageView) findViewById(R.id.profile_image);
        AppCompatTextView gitProfileUserName = (AppCompatTextView) findViewById(R.id.git_user_name);
        AppCompatTextView gitProfileUrl = (AppCompatTextView) findViewById(R.id.profile_url);


        Glide.with(this).load(Uri.parse(getGitUser().getAvatarUrl())).listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                findViewById(R.id.image_loading_progress).setVisibility(View.GONE);
                findViewById(R.id.failed_view).setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                findViewById(R.id.image_loading_progress).setVisibility(View.GONE);
                return false;
            }
        }).into(gitProfileImageView);

        gitProfileUserName.setText(getGitUser().getLogin());
        gitProfileUrl.setText(getGitUser().getHtmlUrl());
        implementDismissinOnTouch();
    }

    private GitUser getGitUser() {
        return getIntent().getParcelableExtra(INTENT_DATA);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void implementDismissinOnTouch() {
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
