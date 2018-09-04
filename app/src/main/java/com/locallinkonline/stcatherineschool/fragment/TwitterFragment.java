package com.locallinkonline.stcatherineschool.fragment;

import com.locallinkonline.locallinkschool.fragment.StandardTwitterFeedFragment;
import com.locallinkonline.stcatherineschool.R;

public class TwitterFragment extends StandardTwitterFeedFragment{
    @Override
    protected String getUsername() {
        return getString(R.string.twitter_name);
    }

    public interface OnFragmentInteractionListener {
    }
}
