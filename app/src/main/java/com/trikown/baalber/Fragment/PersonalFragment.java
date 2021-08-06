package com.trikown.baalber.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.trikown.baalber.R;
import com.trikown.baalber.databinding.FragmentPersonalDetailsBinding;

public class PersonalFragment extends Fragment {
    private FragmentPersonalDetailsBinding b;


    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        b = FragmentPersonalDetailsBinding.inflate(inflater, container, false);
        View v = b.getRoot();

        db = FirebaseFirestore.getInstance();

        getPersonalDetails();

        return v;
    }

    private void getPersonalDetails() {
        //get google id from Shared Preference
        SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.sharedData), Context.MODE_PRIVATE);
        String googleId = preferences.getString("googleId", "0");

        db.collection("ShopOwner").document(googleId).get()
                .addOnSuccessListener(documentSnapshot -> {
                     b.xName.setText(documentSnapshot.getString("UserName"));
                     b.xEmail.setText(documentSnapshot.getString("Email"));
                });
    }
}