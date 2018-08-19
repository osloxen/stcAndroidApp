package com.locallinkonline.locallinkschool.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.locallinkonline.locallinkschool.R;

import androidx.fragment.app.Fragment;

public class ProfileSelectFragment extends Fragment {

    @Override
    public void onCreate (Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_select_layout, container, false);

        return null;
    }

    @Override
    public void onPause () {

    }

    public interface OnFragmentInteractionListner {
        void onFragmentInteraction(Uri uri);
    }
}
