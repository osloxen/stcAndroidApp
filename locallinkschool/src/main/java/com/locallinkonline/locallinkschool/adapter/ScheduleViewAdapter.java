package com.locallinkonline.locallinkschool.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locallinkonline.locallinkschool.R;
import com.locallinkonline.locallinkschool.model.ScheduleModel;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleViewAdapter<T extends ScheduleModel> extends RecyclerView.Adapter<ScheduleViewAdapter.ViewHolder> {

    private List<T> scheduleModels;

    private final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd");

    public ScheduleViewAdapter(List<T> scheduleModels) {
        this.scheduleModels = scheduleModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout constraintLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.standard_schedule_item_layout, parent, false);
        return new ViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(ScheduleViewAdapter.ViewHolder holder, int position) {
        holder.date.setText(sdf.format(scheduleModels.get(position).getDate()));
        holder.menuItem.setText(scheduleModels.get(position).getSummary());
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
        ViewHolder(LinearLayout itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.scheduleDate);
            this.menuItem = itemView.findViewById(R.id.scheduleSummary);
        }
    }
}
