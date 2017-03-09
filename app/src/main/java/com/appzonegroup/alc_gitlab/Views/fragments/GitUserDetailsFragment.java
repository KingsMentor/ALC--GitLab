package com.appzonegroup.alc_gitlab.Views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserDetails;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserDetailAdapter;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.DividerItemDecoration;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.EnhanceRecyclerView;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zone2 on 3/9/17.
 */

public class GitUserDetailsFragment extends Fragment {

    private final String GIT_USER = "com.appzonegroup.alc_gitlab.Views.fragments.GitUserDetailsFragment.GIT_USER";

    CircleImageView circleImageView;
    AppCompatTextView profileName;
    EnhanceRecyclerView enhanceRecyclerView;

    public GitUserDetailsFragment bundleInstance(GitUser gitUser) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(GIT_USER, gitUser);
        setArguments(bundle);
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.git_user_details, container, false);
        circleImageView = (CircleImageView) rootView.findViewById(R.id.profile_image);
        profileName = (AppCompatTextView) rootView.findViewById(R.id.git_user_name);
        enhanceRecyclerView = (EnhanceRecyclerView) rootView.findViewById(R.id.git_user_details_list);
        profileName.setText(getGitUser().getLogin());
        Glide.with(getContext()).load(Uri.parse(getGitUser().getAvatarUrl())).into(circleImageView);

        rootView.findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setText(generateIntentMessage(getGitUser()))
                        .getIntent();
                if (shareIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(shareIntent);
                }
            }
        });
        initRecyclerview();
        retrieveUserDetails(getGitUser(), rootView);
        return rootView;
    }


    private void initRecyclerview() {
        enhanceRecyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getContext(), R.drawable.recycler_divider), false, false));
        enhanceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        enhanceRecyclerView.setHasFixedSize(false);
        enhanceRecyclerView.setNestedScrollingEnabled(false);
    }

    private String generateIntentMessage(GitUser gitUser) {
        return "Check out this awesome developer @" + gitUser.getLogin() + " " + getGitUser().getHtmlUrl();
    }

    private GitUser getGitUser() {
        return getArguments().getParcelable(GIT_USER);
    }

    private void retrieveUserDetails(final GitUser gitUser, final View rootView) {
        GitApplication.getInstance().getDataLoaderController().retrieveUserDetails(gitUser, new Response.Listener<GitUserDetails>() {
            @Override
            public void onResponse(GitUserDetails gitUserDetails) {
                rootView.findViewById(R.id.loadingView).setVisibility(View.GONE);
                enhanceRecyclerView.setAdapter(new GitUserDetailAdapter(gitUserDetails));
            }
        });
    }


}
