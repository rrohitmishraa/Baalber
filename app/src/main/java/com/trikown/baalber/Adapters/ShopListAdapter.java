package com.trikown.baalber.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.trikown.baalber.Activity.BookAppointmentActivity;
import com.trikown.baalber.Models.Shop;
import com.trikown.baalber.R;
import com.trikown.baalber.databinding.ListItemShopsBinding;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    Context ctx;
    ArrayList<Shop> data;

    public ShopListAdapter(Context ctx, ArrayList<Shop> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item_shops, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.b.xShopName.setText(data.get(position).getShopName());
        holder.b.timings.setText(data.get(position).getOpen() + " - " + data.get(position).getClose());
        holder.b.xRating.setText(data.get(position).getRating() + " / 5");

        holder.b.xShopImage.setOnClickListener(v -> {
            Intent i = new Intent(ctx, BookAppointmentActivity.class);
            i.putExtra("shopCode", data.get(position).getShopCode());
            ctx.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ListItemShopsBinding b;

        public ViewHolder(View itemView) {
            super(itemView);
            b = ListItemShopsBinding.bind(itemView);
        }
    }
}
