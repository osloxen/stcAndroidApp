package com.locallinkonline.stcatherineschool.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.locallinkonline.locallinkschool.fragment.StandardTwitterFeedFragment
import com.locallinkonline.stcatherineschool.R

import androidx.appcompat.widget.Toolbar

class TwitterFragment : StandardTwitterFeedFragment() {

    override val username: String
        get() = getString(R.string.twitter_name)

    interface OnFragmentInteractionListener
}
