package com.locallinkonline.locallinkschool.util;

import android.content.Context;

import com.locallinkonline.locallinkschool.adapter.TextListViewAdapter;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewUtils {
    public static void configureStandardRecyclerView(Context context, RecyclerView recyclerView, String[] data, RecyclerView.OnItemTouchListener touchListener) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(new TextListViewAdapter(data));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(touchListener);
    }
}
