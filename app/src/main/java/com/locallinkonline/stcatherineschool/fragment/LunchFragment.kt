package com.locallinkonline.stcatherineschool.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.locallinkonline.locallinkschool.fragment.StandardScheduleListViewFragment
import com.locallinkonline.locallinkschool.listener.StandardTouchListener
import com.locallinkonline.locallinkschool.view.LiveDataViewModel
import com.locallinkonline.stcatherineschool.R
import com.locallinkonline.stcatherineschool.room.entity.LunchEntity
import com.locallinkonline.stcatherineschool.view.LunchViewModel

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LunchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LunchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LunchFragment : StandardScheduleListViewFragment<LunchEntity>() {

    private var mListener: OnFragmentInteractionListener? = null

    override val staticData: List<LunchEntity>
        get() = emptyList<LunchEntity>()

    override val viewModel: LiveDataViewModel<List<LunchEntity>>?
        get() = ViewModelProviders.of(this).get<LunchViewModel>(LunchViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            arguments!!.getString(ARG_PARAM1)
            arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val toolbar = container!!.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setTitle(R.string.lunch_title)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getClickListener(view: View): StandardTouchListener.ClickListener {
        return object : StandardTouchListener.ClickListener {
            override fun onClick(view: View, position: Int) {
                //Do Nothing
            }

            override fun onLongClick(view: View?, position: Int) {
                //Do Nothing
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LunchFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): LunchFragment {
            val fragment = LunchFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
