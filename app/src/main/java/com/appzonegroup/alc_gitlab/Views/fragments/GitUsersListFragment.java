package com.appzonegroup.alc_gitlab.Views.fragments;

import android.animation.Animator;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Models.enums.Sort;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.UpdateNotifier;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.activities.MainActivity;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.DividerItemDecoration;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.EnhanceRecyclerView;
import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserAdapterViewHolder;

/**
 * Created by zone2 on 3/9/17.
 */

public class GitUsersListFragment extends UpdateNotifier implements EnhanceRecyclerView.listenToScroll {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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
                    GitApplication.getInstance().getDataLoaderController().startLoadingData(getSortBy(-1));
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        setHasOptionsMenu(true);
        initSideBar(rootView);
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

    private void initSideBar(View rootView) {

        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);

        // set a custom shadow that overlays the main content when the drawer opens
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

//
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };


        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView = (NavigationView) rootView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (loadingView.getVisibility() == View.VISIBLE) {
                    return false;
                } else {
                    if (!item.isChecked()) {
                        item.setChecked(true);
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        showLoadingView();
                        GitApplication.getInstance().getDataLoaderController().startLoadingData(getSortBy(item.getItemId()));
                    }

                    return true;
                }
            }
        });

    }

    NavigationView navigationView;

    @Override
    public void reachedEndOfList() {
        showLoadingView();
        GitApplication.getInstance().getDataLoaderController().startLoadingData(getSortBy(-1));
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


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggle
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerLayout.openDrawer(Gravity.RIGHT);
        return super.onOptionsItemSelected(item);
    }

    private Sort getSortBy(int itemId) {
        return (itemId == -1 || itemId == R.id.followers) ? Sort.FOLLOWERS : itemId == R.id.repo ? Sort.REPO : Sort.JOINED;

    }

    private int getCheckedItem() {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.isChecked()) {
                return i;
            }
        }

        return -1;
    }
}
