package com.trikown.baalber.Fragment.BottomNav.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.trikown.baalber.Adapters.ShopListAdapter;
import com.trikown.baalber.R;

import java.util.ArrayList;

public class HomeFragmentC extends Fragment {

    View v;
    RecyclerView mHomeRecViewC;
    ImageView mBtnSearch;
    ShopListAdapter adapter;
    ArrayList<String> data;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_customer_home, container, false);

        init();

        return v;
    }

    private void init() {
        data = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        getShopList();

        mHomeRecViewC = v.findViewById(R.id.xHomeRecyclerviewC);
        mHomeRecViewC.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShopListAdapter(getContext(), data);
        mHomeRecViewC.setAdapter(adapter);
        mBtnSearch = v.findViewById(R.id.xBtnSearch);

        onClicks();
    }

    private void onClicks() {
        mBtnSearch.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Search", Toast.LENGTH_SHORT).show();
        });
    }

    public void getShopList() {
        Query query = db.collection("Shops");
        query.addSnapshotListener((value, error) -> {
            for (DocumentChange doc : value.getDocumentChanges()) {
                String id = doc.getDocument().getId();

                db.collection("Shops").document(id).get()
                        .addOnSuccessListener(documentSnapshot -> {
                            data.add(documentSnapshot.getString("Name"));

                            adapter.notifyDataSetChanged();
                        });
            }
        });
    }
}