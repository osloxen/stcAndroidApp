package com.locallinkonline.stcatherineschool.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.adapter.LunchDisplayAdapter;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;
import com.locallinkonline.stcatherineschool.view.AdViewModel;
import com.locallinkonline.stcatherineschool.view.LunchViewModel;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static com.locallinkonline.stcatherineschool.util.AdUtils.changeAdView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LunchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LunchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LunchFragment extends Fragment {

    private ListView listView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    public LunchFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LunchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LunchFragment newInstance(String param1, String param2) {
        LunchFragment fragment = new LunchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lunch, container, false);

        listView = view.findViewById(R.id.lunchDetailsListView);

        Toolbar toolbar = container.findViewById(R.id.toolbar);

        toolbar.setTitle(R.string.lunch_title);

        LunchViewModel viewModel = ViewModelProviders.of(this).get(LunchViewModel.class);

        viewModel.getData().observe(this, data -> {
            this.getMainListView().setAdapter(
                    new LunchDisplayAdapter(this.getContext(),
                            data.getLunchScheduleList().toArray(
                                    new Lunch[data.getLunchScheduleList().size()])));
        });

        AdViewModel adViewModel = ViewModelProviders.of(this).get(AdViewModel.class);

        adViewModel.getCurrentAd().observe(this, data -> changeAdView(view, data));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public ListView getMainListView() {
        return this.listView;
    }
}
