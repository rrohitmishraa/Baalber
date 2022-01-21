package com.trikown.baalber.Repository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trikown.baalber.Adapters.AppointmentsAdapter;
import com.trikown.baalber.Adapters.ShopListAdapter;
import com.trikown.baalber.Adapters.TodayAdapter;
import com.trikown.baalber.Interface.CustomerDetailsInterface;
import com.trikown.baalber.Interface.ShopDetailsInterface;
import com.trikown.baalber.Interface.UsedTimeSlotsInterface;
import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.Models.Customer;
import com.trikown.baalber.Models.Shop;

import java.util.ArrayList;

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
                    public void onDataChange(DataSnapshot snapshot) {
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
        db.child(CUSTOMERS)
                .child(appointment.getUserCode())
                .child(APPOINTMENTS)
                .child(appointment.getTime())
                .setValue(appointment);
    }

    public void getAppointmentsForCustomer(String customerId, AppointmentsAdapter adapter, ArrayList<Appointment> data) {
        db.child(CUSTOMERS).child(customerId).child(APPOINTMENTS)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {

                            db.child(CUSTOMERS).child(customerId).child(APPOINTMENTS).child(ds.getKey())
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            Appointment appointment = ds.getValue(Appointment.class);
                                            data.add(appointment);
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

    public void getAppointmentsForShop(String shopCode, String date, TodayAdapter adapter, ArrayList<Appointment> data) {
        db.child(APPOINTMENTS).child(shopCode).child(date).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    db.child(APPOINTMENTS).child(shopCode).child(ds.getKey())
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Appointment appointment = ds.getValue(Appointment.class);
                                    data.add(appointment);
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

    public void addShop(Shop shop) {
        db.child(APPOINTMENTS)
                .child(shop.getShopName())
                .setValue(shop);
    }

}