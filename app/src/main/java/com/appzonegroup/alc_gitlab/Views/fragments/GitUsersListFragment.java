package com.appzonegroup.alc_gitlab.Views.fragments;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.UpdateNotifier;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.MainActivity;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.DividerItemDecoration;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.EnhanceRecyclerView;
import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserAdapterViewHolder;

/**
 * Created by zone2 on 3/9/17.
 */

public class GitUsersListFragment extends UpdateNotifier implements EnhanceRecyclerView.listenToScroll {


    private EnhanceRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View loadingView;
    private View errorPage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.git_user_list, container, false);
        recyclerView = (EnhanceRecyclerView) rootView.findViewById(R.id.git_users);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        loadingView = rootView.findViewById(R.id.loadingView);
        errorPage = rootView.findViewById(R.id.error_page);
        recyclerView.listen(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(getContext(), R.drawable.padded_divider), false, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (recyclerView.getAdapter() == null) {
                    GitApplication.getInstance().getDataLoaderController().startLoadingData();
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void notifyAdapterCreated(GitUserListAdapter adapter) {

        recyclerView.setAdapter(adapter);
        if (isErrorPageShowing())
            hideErrorPage();
        hideLoadingView();
    }

    @Override
    public void failedDataRefresh() {
        if (recyclerView.getAdapter() == null)
            showErrorPage();
        else
            hideLoadingView();
    }

    @Override
    public void notifyAdapterUpdated(GitUserRequestData gitUserRequestData) {
        hideLoadingView();
    }

    @Override
    public void onItemSelected(GitUserAdapterViewHolder holder, GitUser gitUser, int position) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        GitUserDetailsFragment gitUserDetailsFragment = new GitUserDetailsFragment().bundleInstance(gitUser);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            gitUserDetailsFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image));


            gitUserDetailsFragment.setSharedElementReturnTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image));
            gitUserDetailsFragment.setExitTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));

            gitUserDetailsFragment.setSharedElementEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image));
            gitUserDetailsFragment.setEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));

            fragmentTransaction.addSharedElement(holder.gitUserProfileImageView, getString(R.string.profile_image_transition));
            fragmentTransaction.addSharedElement(holder.gitUserProfileNameField, getString(R.string.profile_name_transition));

        }

        fragmentTransaction.add(R.id.root_frame, gitUserDetailsFragment).commitAllowingStateLoss();

    }


    @Override
    public void reachedEndOfList() {
        showLoadingView();
        GitApplication.getInstance().getDataLoaderController().startLoadingData();
    }


    private void showErrorPage() {
        swipeRefreshLayout.setRefreshing(false);
        errorPage.setVisibility(View.VISIBLE);
    }

    private void hideErrorPage() {
        swipeRefreshLayout.setRefreshing(false);
        errorPage.setVisibility(View.GONE);
    }

    private boolean isErrorPageShowing() {
        return errorPage.getVisibility() == View.VISIBLE;
    }

    private void showLoadingView() {
        loadingView.setVisibility(View.VISIBLE);
        loadingView.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    private void hideLoadingView() {
        swipeRefreshLayout.setRefreshing(false);
        loadingView.animate().translationY(loadingView.getHeight()).setInterpolator(new AccelerateInterpolator(2)).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                loadingView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity) getActivity()).menu != null)
            ((MainActivity) getActivity()).menu.findItem(R.id.filter).setVisible(true);
    }

}
