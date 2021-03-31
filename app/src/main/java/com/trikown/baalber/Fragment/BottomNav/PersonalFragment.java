package com.trikown.baalber.Fragment.BottomNav;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.trikown.baalber.R;

public class PersonalFragment extends Fragment {

    View v;
    TextInputEditText mName, mEmail;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_personal_details, container, false);

        db = FirebaseFirestore.getInstance();

        mName = v.findViewById(R.id.xName);
        mEmail = v.findViewById(R.id.xEmail);

        getPersonalDetails();

        return v;
    }

    private void getPersonalDetails() {
        //get google id from Shared Preference
        SharedPreferences preferences = getContext().getSharedPreferences(getString(R.string.sharedData), Context.MODE_PRIVATE);
        String googleId = preferences.getString("googleId", "0");

        db.collection("ShopOwner").document(googleId).get()
                .addOnSuccessListener(documentSnapshot -> {
                     mName.setText(documentSnapshot.getString("UserName"));
                     mEmail.setText(documentSnapshot.getString("Email"));
                });
    }
}