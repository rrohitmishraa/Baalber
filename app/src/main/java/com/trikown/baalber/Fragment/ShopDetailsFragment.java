package com.trikown.baalber.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trikown.baalber.R;
import com.trikown.baalber.databinding.FragmentPersonalDetailsBinding;

public class ShopDetailsFragment extends Fragment {

    private FragmentPersonalDetailsBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentPersonalDetailsBinding.inflate(inflater, container, false);
        View v = b.getRoot();


        return v;
    }
}