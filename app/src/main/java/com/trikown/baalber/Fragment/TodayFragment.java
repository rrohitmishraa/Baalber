package com.trikown.baalber.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.trikown.baalber.R;
import com.trikown.baalber.databinding.FragmentTodayBinding;

public class TodayFragment extends Fragment {
    private FragmentTodayBinding b;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentTodayBinding.inflate(inflater, container, false);
        View v = b.getRoot();




        return v;
    }
}