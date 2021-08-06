package com.trikown.baalber.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.trikown.baalber.Adapters.ShopListAdapter;
import com.trikown.baalber.Models.Shop;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.databinding.FragmentHomeCustomerBinding;

import java.util.ArrayList;

public class HomeFragmentC extends Fragment {

    ArrayList<Shop> data;

    ShopListAdapter adapter;
    private FragmentHomeCustomerBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentHomeCustomerBinding.inflate(inflater, container, false);
        View v = b.getRoot();
        init();

        return v;
    }

    private void init() {
        data = new ArrayList<>();
        b.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShopListAdapter(getContext(), data);
        b.recyclerView.setAdapter(adapter);

        new Repo().getShopList(data, adapter);

        b.xToolbar.xBtnBack.setVisibility(View.GONE);

        onClicks();
    }

    private void onClicks() {
        b.btnSearch.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Search", Toast.LENGTH_SHORT).show();
        });
    }
}