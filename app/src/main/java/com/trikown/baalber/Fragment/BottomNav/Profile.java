package com.trikown.baalber.Fragment.BottomNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trikown.baalber.R;

public class Profile extends Fragment {

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.profile_fragment, container, false);;

        return v;
    }
}