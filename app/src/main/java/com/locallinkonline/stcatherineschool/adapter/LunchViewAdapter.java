package com.locallinkonline.stcatherineschool.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class LunchViewAdapter extends RecyclerView.Adapter<LunchViewAdapter.ViewHolder> {

    private List<LunchEntity> lunches;

    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

    public LunchViewAdapter(List<LunchEntity> lunches) {
        this.lunches = lunches;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lunch_layout, parent, false);
        return new ViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.date.setText(sdf.format(lunches.get(position).getEventDate()));
        holder.menuItem.setText(lunches.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return lunches.size();
    }

    public void setLunches(List<LunchEntity> newLunches) {
        if(newLunches == null) {
            return;
        }

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return lunches != null ? lunches.size() : 0;
            }

            @Override
            public int getNewListSize() {
                return newLunches.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return lunches.get(oldItemPosition) == newLunches.get(newItemPosition);
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                LunchEntity oldLunch = lunches.get(oldItemPosition);
                LunchEntity newLunch = newLunches.get(oldItemPosition);
                return oldLunch.equals(newLunch);
            }
        });
        this.lunches = newLunches;
        result.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView menuItem;
        ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.lunchDateTV);
            this.menuItem = itemView.findViewById(R.id.lunchMenuTV);
        }
    }
}
