package com.locallinkonline.locallinkschool.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locallinkonline.locallinkschool.R;
import com.locallinkonline.locallinkschool.adapter.TextListViewAdapter;
import com.locallinkonline.locallinkschool.listener.StandardTouchListener;
import com.locallinkonline.locallinkschool.view.AdViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import static com.locallinkonline.locallinkschool.util.AdUtils.changeAdView;
import static com.locallinkonline.locallinkschool.util.ViewUtils.configureStandardRecyclerView;

public abstract class StandardTextViewListFragment extends StandardRecyclerViewFragment<String[]> {

    @Override
    protected RecyclerView.Adapter getViewAdapter(String[] data) {
        return new TextListViewAdapter(data);
    }
}
