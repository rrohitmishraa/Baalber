package com.trikown.baalber.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.trikown.baalber.Interface.SlotsClickedListener;
import com.trikown.baalber.R;
import com.trikown.baalber.databinding.ListItemSlotsBinding;

import java.util.ArrayList;

public class SlotsListAdapter extends RecyclerView.Adapter<SlotsListAdapter.ViewHolder> {

    Context context;
    ArrayList<String> timeList;
    private SlotsClickedListener mListener;

    public SlotsListAdapter(Context context, ArrayList<String> timeList, SlotsClickedListener mListener) {
        this.context = context;
        this.timeList = timeList;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_slots, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.b.timeSlot.setText(timeList.get(position));
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemSlotsBinding b;

        public ViewHolder(View itemView) {
            super(itemView);
            b = ListItemSlotsBinding.bind(itemView);
            itemView.setOnClickListener(v -> {
                mListener.onSlotClicked(getAdapterPosition());
            });
        }
    }
}
