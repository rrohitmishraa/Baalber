package com.trikown.baalber.Repository;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.trikown.baalber.Adapters.ShopListAdapter;
import com.trikown.baalber.Interface.ShopDetailsInterface;
import com.trikown.baalber.Models.Shop;

import java.util.ArrayList;

public class Repo {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Query query = db.collection("Shops");

    public void getShopList(ArrayList<Shop> data, ShopListAdapter adapter) {
        query.addSnapshotListener((value, error) -> {
            for (DocumentChange doc : value.getDocumentChanges()) {
                String id = doc.getDocument().getId();

                db.collection("Shops").document(id).get()
                        .addOnSuccessListener(documentSnapshot -> {
                            data.add(documentSnapshot.toObject(Shop.class));
                            adapter.notifyDataSetChanged();
                        });
            }
        });
    }

    public void getShopDetails(String shopCode, ShopDetailsInterface shopDetailsInterface) {
        db.collection("Shops").document(shopCode).get()
                .addOnSuccessListener(documentSnapshot -> {
                    shopDetailsInterface.getShopDetails(documentSnapshot.toObject(Shop.class));
                });
    }
}