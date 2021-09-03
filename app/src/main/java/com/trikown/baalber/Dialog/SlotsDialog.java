package com.trikown.baalber.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.trikown.baalber.Adapters.SlotsListAdapter;
import com.trikown.baalber.Interface.DialogToActivity;
import com.trikown.baalber.Interface.SlotsClickedListener;
import com.trikown.baalber.Repository.Repo;
import com.trikown.baalber.databinding.DialogSlotsBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SlotsDialog extends AppCompatDialogFragment implements SlotsClickedListener {

    DialogSlotsBinding b;
    ArrayList<String> completeTimeList;
    DialogToActivity mListener;
    String date, shopCode;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (DialogToActivity) getContext();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        b = DialogSlotsBinding.inflate(LayoutInflater.from(getActivity()));
        completeTimeList = new ArrayList<>();

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getActivity());
        builder.setView(b.getRoot())
                .setTitle("Available Time Slots: ");

        date = getArguments().getString("Date");
        shopCode = getArguments().getString("ShopCode");

        new Repo().getShopDetails(shopCode, shop -> {
            availableSlots(shop.getOpen(), shop.getClose());
        });

        return builder.create();
    }

    private void availableSlots(String openTime, String closeTime) {
        new Repo().getAllBookedTimeSlots(date, shopCode, usedTimeSlots -> {

            SimpleDateFormat df = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            //String time = df.format(new Date());

            Date oTime = null, cTime = null;
            try {
                oTime = df.parse(openTime);
                cTime = df.parse(closeTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();

            cal.setTime(oTime);
            completeTimeList.add(df.format(cal.getTime()));

            while (cal.getTime().before(cTime)) {
                cal.add(Calendar.MINUTE, 15);
                completeTimeList.add(df.format(cal.getTime()));
            }

            completeTimeList.remove(completeTimeList.size() - 1);
            completeTimeList.removeAll(usedTimeSlots);

            SlotsListAdapter adapter = new SlotsListAdapter(getContext(), completeTimeList, this);
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
            b.recyclerView.setLayoutManager(layoutManager);
            b.recyclerView.setAdapter(adapter);

        });
    }

    @Override
    public void onSlotClicked(int pos) {
        String time = completeTimeList.get(pos);
        mListener.getData(time);
        dismiss();
    }
}
