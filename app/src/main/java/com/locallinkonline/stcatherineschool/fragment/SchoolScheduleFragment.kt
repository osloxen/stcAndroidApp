package com.locallinkonline.stcatherineschool.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.locallinkonline.locallinkschool.fragment.StandardScheduleListViewFragment
import com.locallinkonline.locallinkschool.listener.StandardTouchListener
import com.locallinkonline.locallinkschool.view.LiveDataViewModel
import com.locallinkonline.stcatherineschool.R
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity
import com.locallinkonline.stcatherineschool.view.SchoolScheduleViewModel

import androidx.lifecycle.ViewModelProviders

class SchoolScheduleFragment : StandardScheduleListViewFragment<ScheduleEntity>() {

    override val staticData: List<ScheduleEntity>
        get() = emptyList()

    override val viewModel: LiveDataViewModel<List<ScheduleEntity>>?
        get() {
            val viewModel = ViewModelProviders.of(this).get<SchoolScheduleViewModel>(SchoolScheduleViewModel::class.java)

            viewModel.setArguments(this.arguments)

            return viewModel
        }

    override fun getClickListener(view: View): StandardTouchListener.ClickListener {
        return object : StandardTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {

            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }
    }

    interface OnFragmentInteractionListener
}
