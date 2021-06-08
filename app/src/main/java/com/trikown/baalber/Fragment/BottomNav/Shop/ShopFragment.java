package com.trikown.baalber.Fragment.BottomNav.Shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trikown.baalber.R;

public class ShopFragment extends Fragment {

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_shop_details, container, false);



        return v;
    }
}