package com.appzonegroup.alc_gitlab.Views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appzonegroup.alc_gitlab.R;
import com.appzonegroup.alc_gitlab.Views.activities.MainActivity;

/**
 * Created by zone2 on 3/9/17.
 */

public class Welcome extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.welcome_screen, container, false);
    }
}
