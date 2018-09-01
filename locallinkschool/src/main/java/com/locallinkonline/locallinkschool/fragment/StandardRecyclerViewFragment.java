package com.locallinkonline.locallinkschool.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locallinkonline.locallinkschool.R;
import com.locallinkonline.locallinkschool.listener.StandardTouchListener;
import com.locallinkonline.locallinkschool.view.AdViewModel;
import com.locallinkonline.locallinkschool.view.LiveDataViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.locallinkonline.locallinkschool.util.AdUtils.changeAdView;

public abstract class StandardRecyclerViewFragment<T> extends Fragment {

    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.standard_list_layout, container, false);

        recyclerView = view.findViewById(R.id.standard_recycle_view);

        RecyclerView.OnItemTouchListener touchListener = new StandardTouchListener(
                getContext(),
                recyclerView,
                getClickListener(recyclerView));

        configureStandardRecyclerView(
                getContext(),
                recyclerView,
                getStaticData(),
                touchListener);

        AdViewModel adViewModel = ViewModelProviders.of(getActivity()).get(AdViewModel.class);

        adViewModel.getData().observe(this, data -> changeAdView(view, data));

        LiveDataViewModel<T> viewModel = getViewModel();

        if(viewModel != null) {
            configureViewModel(viewModel);
        }

        // Inflate the layout for this fragment
        return view;
    }


    private void configureStandardRecyclerView(Context context, RecyclerView recyclerView, T data, RecyclerView.OnItemTouchListener touchListener) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(getViewAdapter(data));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(touchListener);
    }

    protected abstract T getStaticData();

    protected abstract RecyclerView.Adapter getViewAdapter(T data);

    protected abstract StandardTouchListener.ClickListener getClickListener(View view);

    protected abstract void configureViewModel(LiveDataViewModel<T> viewModel);

    protected abstract LiveDataViewModel<T> getViewModel();
}
