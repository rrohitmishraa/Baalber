package com.trikown.baalber.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.trikown.baalber.databinding.FragmentAppointmentsBinding;
import com.trikown.baalber.databinding.FragmentHomeCustomerBinding;

public class AppointmentsFragment extends Fragment {
    private FragmentAppointmentsBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentAppointmentsBinding.inflate(inflater, container, false);
        View v = b.getRoot();
        return v;
    }
}