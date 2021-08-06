package com.trikown.baalber.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trikown.baalber.R;
import com.trikown.baalber.databinding.FragmentTomorrowBinding;

public class TomorrowFragment extends Fragment {
    private FragmentTomorrowBinding b;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentTomorrowBinding.inflate(inflater, container, false);
        View v = b.getRoot();

        return v;
    }
}