package com.trikown.baalber.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.trikown.baalber.Models.Appointment;
import com.trikown.baalber.R;
import com.trikown.baalber.databinding.ListItemAppointmentsBinding;

import java.util.ArrayList;

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Appointment> data;

    public AppointmentsAdapter(Context ctx, ArrayList<Appointment> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item_appointments, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.b.date.setText(data.get(position).getDate());
        holder.b.time.setText(data.get(position).getTime());
        holder.b.status.setText(data.get(position).getStatus());
        holder.b.shopName.setText(data.get(position).getShopCode());

        if (data.get(position).getHaircut() == 1 && data.get(position).getShave() == 1) {
            holder.b.requirement.setText("Haircut \u2022 Shave");
        } else if (data.get(position).getHaircut() == 1) {
            holder.b.requirement.setText("Haircut");
        } else {
            holder.b.requirement.setText("Shave");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemAppointmentsBinding b;

        public ViewHolder(View itemView) {
            super(itemView);
            b = ListItemAppointmentsBinding.bind(itemView);
        }
    }
}
