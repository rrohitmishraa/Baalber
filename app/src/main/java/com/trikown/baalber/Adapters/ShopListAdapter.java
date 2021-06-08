package com.trikown.baalber.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.trikown.baalber.Activity.Customer.BookAppointment;
import com.trikown.baalber.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    Context ctx;
    ArrayList<String> data;

    public ShopListAdapter(Context ctx, ArrayList<String> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shops_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.mShopName.setText(data.get(position));

        holder.mCard.setOnClickListener(v -> {
            Intent i = new Intent(ctx, BookAppointment.class);
            ctx.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mShopName;
        CardView mCard;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            mShopName = itemView.findViewById(R.id.xShopName);
            mCard = itemView.findViewById(R.id.xCard);
        }
    }
}
