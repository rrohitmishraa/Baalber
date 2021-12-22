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
import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.R;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.databinding.FragmentAppointmentsBinding;
import com.trikown.baalber.databinding.FragmentHomeCustomerBinding;

import java.util.ArrayList;

public class AppointmentsFragment extends Fragment {
    private FragmentAppointmentsBinding b;

    ArrayList<Appointment> data;
    AppointmentsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentAppointmentsBinding.inflate(inflater, container, false);
        View v = b.getRoot();

        init();

        return v;
    }

    private void init() {
        data = new ArrayList<>();
        b.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AppointmentsAdapter(getContext(), data);
        b.recyclerView.setAdapter(adapter);

        SharedPreferences sp = getContext().getSharedPreferences(getString(R.string.sharedPreference), Context.MODE_PRIVATE);

        new Repo().getAppointmentsForCustomer(sp.getString("googleId", null), adapter, data);
    }
}