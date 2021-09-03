package com.trikown.baalber.Repository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trikown.baalber.Adapters.ShopListAdapter;
import com.trikown.baalber.Interface.CustomerDetailsInterface;
import com.trikown.baalber.Interface.ShopDetailsInterface;
import com.trikown.baalber.Interface.UsedTimeSlotsInterface;
import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.Models.Customer;
import com.trikown.baalber.Models.Shop;

import java.util.ArrayList;
import java.util.HashMap;

public class Repo {
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    String SHOPS = "Shops", APPOINTMENTS = "Appointments", CUSTOMERS = "Customer", SHOPOWNERS = "ShopOwner";

    public void getShopList(ArrayList<Shop> data, ShopListAdapter adapter) {
        db.child(SHOPS)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {

                            db.child(SHOPS).child(ds.getKey()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                    Shop shop = ds.getValue(Shop.class);
                                    data.add(shop);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void getShopDetails(String shopCode, ShopDetailsInterface shopDetailsInterface) {
        db.child(SHOPS).child(shopCode)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Shop shop = snapshot.getValue(Shop.class);
                        shopDetailsInterface.getShopDetails(shop);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void getAllBookedTimeSlots(String date, String shopCode, UsedTimeSlotsInterface usedTimeSlotsInterface) {
        ArrayList<String> usedTimeSlots = new ArrayList<>();

        db.child(APPOINTMENTS).child(shopCode)
                .child(date.replace(" ", ""))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            usedTimeSlots.add(ds.getKey());
                        }

                        usedTimeSlotsInterface.getUsedTimeSlots(usedTimeSlots);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }

    public void getCustomerDetails(String accountType, String userId, CustomerDetailsInterface customerDetailsInterface) {
        db.child(accountType)
                .child(userId)
                .child("CustomerInfo")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Customer customer = snapshot.getValue(Customer.class);
                        customerDetailsInterface.getCustomerDetails(customer);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void createAppointment(Appointment appointment) {
        db.child(APPOINTMENTS)
                .child(appointment.getShopCode())
                .child(appointment.getDate().replace(" ", ""))
                .child(appointment.getTime())
                .setValue(appointment);
    }

    public void addAppointmentForCustomer(Appointment appointment) {
        HashMap<String, Object> appointmentInfo = new HashMap<>();
        appointmentInfo.put("ShopCode", appointment.getShopCode());
        appointmentInfo.put("Date", appointment.getDate());
        appointmentInfo.put("Time", appointment.getTime());

        db.child(CUSTOMERS)
                .child(appointment.getUserCode())
                .child(APPOINTMENTS)
                .push()
                .setValue(appointmentInfo);
    }
}