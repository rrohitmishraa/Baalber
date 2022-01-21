package com.trikown.baalber.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.trikown.baalber.Adapters.AppointmentsAdapter;
import com.trikown.baalber.Adapters.TodayAdapter;
import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.R;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.databinding.FragmentTodayBinding;

import java.util.ArrayList;

public class TodayFragment extends Fragment {
    private FragmentTodayBinding b;

    ArrayList<Appointment> data;
    TodayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentTodayBinding.inflate(inflater, container, false);
        View v = b.getRoot();

        init();

        return v;
    }

    private void init() {
        data = new ArrayList<>();
        b.xTodayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TodayAdapter(getContext(), data);
        b.xTodayRecyclerView.setAdapter(adapter);

        SharedPreferences sp = getContext().getSharedPreferences(getString(R.string.sharedPreference), Context.MODE_PRIVATE);

        new Repo().getAppointmentsForShop("shop1", "January12,2022", adapter, data);
    }
}