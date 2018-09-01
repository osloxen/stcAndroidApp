package com.locallinkonline.locallinkschool.adapter;

import android.content.res.Resources;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.locallinkonline.locallinkschool.R;
import com.locallinkonline.locallinkschool.model.ScheduleModel;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleViewAdapter<T extends ScheduleModel> extends RecyclerView.Adapter<ScheduleViewAdapter.ViewHolder> {

    private List<T> scheduleModels;

    private final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd");

    public ScheduleViewAdapter(List<T> scheduleModels) {
        this.scheduleModels = scheduleModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MaterialCardView layout = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.standard_schedule_item_layout, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ScheduleViewAdapter.ViewHolder holder, int position) {
        ScheduleModel model = scheduleModels.get(position);

        String time = sdf.format(model.getDate());

        if(model.getStartTime() != null && model.getEndTime() != null && !model.getStartTime().equals(model.getEndTime())) {
            holder.duration.setText(model.getStartTime() + " - " + model.getEndTime());
        }

        holder.date.setText(time);
        holder.menuItem.setText(model.getSummary());
    }

    @Override
    public int getItemCount() {
        return scheduleModels != null ? scheduleModels.size() : 0;
    }

    public void setScheduleModels(List<T> newScheduleModels) {
        if(newScheduleModels == null) {
            return;
        }

        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return scheduleModels != null ? scheduleModels.size() : 0;
            }

            @Override
            public int getNewListSize() {
                return newScheduleModels.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return scheduleModels.get(oldItemPosition) == newScheduleModels.get(newItemPosition);
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                ScheduleModel oldScheduleModel = scheduleModels.get(oldItemPosition);
                ScheduleModel newScheduleModel = newScheduleModels.get(oldItemPosition);
                return oldScheduleModel.equals(newScheduleModel);
            }
        });
        this.scheduleModels = newScheduleModels;
        result.dispatchUpdatesTo(this);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView menuItem;
        TextView duration;
        ViewHolder(MaterialCardView itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.scheduleDate);
            this.menuItem = itemView.findViewById(R.id.scheduleSummary);
            this.duration = itemView.findViewById(R.id.scheduleDuration);
        }
    }
}
