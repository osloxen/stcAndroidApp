package com.locallinkonline.stcatherineschool.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locallinkonline.locallinkschool.fragment.StandardTwitterFeedFragment;
import com.locallinkonline.stcatherineschool.R;

import androidx.appcompat.widget.Toolbar;

public class TwitterFragment extends StandardTwitterFeedFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toolbar toolbar = container.findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.twitter_title);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected String getUsername() {
        return getString(R.string.twitter_name);
    }

    public interface OnFragmentInteractionListener {
    }
}
