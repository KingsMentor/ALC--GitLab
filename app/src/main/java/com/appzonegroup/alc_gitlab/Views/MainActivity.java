package com.appzonegroup.alc_gitlab.Views;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.appzonegroup.alc_gitlab.Models.GitUser;
import com.appzonegroup.alc_gitlab.Models.GitUserRequestData;
import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.Presenters.controllers.notifiers.ActivityNotifier;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.adapters.GitUserListAdapter;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.DividerItemDecoration;
import com.appzonegroup.alc_gitlab.Views.enhanceViews.EnhanceRecyclerView;
import com.appzonegroup.alc_gitlab.Views.fragments.GitUserDetailsFragment;
import com.appzonegroup.alc_gitlab.Views.viewHolders.GitUserAdapterViewHolder;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends ActivityNotifier implements EnhanceRecyclerView.listenToScroll {

    private EnhanceRecyclerView recyclerView;
    private View loadingView;
    private View errorPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (EnhanceRecyclerView) findViewById(R.id.git_users);
        loadingView = findViewById(R.id.loadingView);
        errorPage = findViewById(R.id.error_page);
        addReloadListener();
        recyclerView.listen(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.recycler_divider), false, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(ContextCompat.getDrawable(this, R.drawable.github_circle));
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        GitUserDetailsFragment gitUserDetailsFragment = new GitUserDetailsFragment().bundleInstance(gitUser);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            gitUserDetailsFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image));


            gitUserDetailsFragment.setSharedElementReturnTransition(TransitionInflater.from(
                    this).inflateTransition(R.transition.change_image));
            gitUserDetailsFragment.setExitTransition(TransitionInflater.from(
                    this).inflateTransition(android.R.transition.fade));

            gitUserDetailsFragment.setSharedElementEnterTransition(TransitionInflater.from(
                    this).inflateTransition(R.transition.change_image));
            gitUserDetailsFragment.setEnterTransition(TransitionInflater.from(
                    this).inflateTransition(android.R.transition.fade));

            fragmentTransaction.addSharedElement(holder.gitUserProfileImageView, getString(R.string.profile_image_transition));
            fragmentTransaction.addSharedElement(holder.gitUserProfileNameField, getString(R.string.profile_name_transition));

        }

        fragmentTransaction.replace(R.id.roof_frame, gitUserDetailsFragment).commitAllowingStateLoss();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void reachedEndOfList() {
        showLoadingView();
        GitApplication.getInstance().getDataLoaderController().startLoadingData();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    private void addReloadListener() {
        findViewById(R.id.reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingView();
                GitApplication.getInstance().getDataLoaderController().startLoadingData();
            }
        });
    }

    private void showErrorPage() {
        errorPage.setVisibility(View.VISIBLE);
    }

    private void hideErrorPage() {
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


}


