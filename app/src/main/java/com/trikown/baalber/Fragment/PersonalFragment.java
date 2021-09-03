package com.trikown.baalber.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;
import com.trikown.baalber.R;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.databinding.FragmentPersonalDetailsBinding;

public class PersonalFragment extends Fragment {
    private FragmentPersonalDetailsBinding b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentPersonalDetailsBinding.inflate(inflater, container, false);
        View v = b.getRoot();

        getPersonalDetails();

        return v;
    }

    private void getPersonalDetails() {
        //get googleId and AccountType from Shared Preference
        SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.sharedPreference), Context.MODE_PRIVATE);
        String googleId = preferences.getString("googleId", null);
        String accountType = preferences.getString("accountType", null);

        //Get Customer details FireStore
        new Repo().getCustomerDetails(accountType, googleId, customer -> {
            b.xName.setText(customer.getUserName());
            b.xEmail.setText(customer.getEmail());

            Picasso.get().
                    load(customer.getProfilePic())
                    .into(b.xProfilePic);

        });
    }
}