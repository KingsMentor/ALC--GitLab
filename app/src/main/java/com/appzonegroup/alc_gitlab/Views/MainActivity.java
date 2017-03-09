package com.appzonegroup.alc_gitlab.Views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.appzonegroup.alc_gitlab.Presenters.application.GitApplication;
import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.fragments.GitUsersListFragment;
import com.appzonegroup.alc_gitlab.Views.fragments.Welcome;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(ContextCompat.getDrawable(this, R.drawable.github_circle));

        getSupportFragmentManager().beginTransaction().replace(R.id.root_frame, new Welcome())
                .commitAllowingStateLoss();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().replace(R.id.root_frame, new GitUsersListFragment())
                        .commitAllowingStateLoss();
                getSupportActionBar().show();
                GitApplication.getInstance().getDataLoaderController().startLoadingData();
            }
        }, 5000);

    }

    public Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        int fragmentInBackStack = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentInBackStack == 1) {
            menu.findItem(R.id.filter).setVisible(true);
        }
        super.onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}


