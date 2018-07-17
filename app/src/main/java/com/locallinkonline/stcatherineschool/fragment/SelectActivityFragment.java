package com.locallinkonline.stcatherineschool.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.listener.StandardTouchListener;
import com.locallinkonline.stcatherineschool.view.AdViewModel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import static com.locallinkonline.stcatherineschool.util.AdUtils.changeAdView;
import static com.locallinkonline.stcatherineschool.util.ViewUtils.configureStandardRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectActivityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectActivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectActivityFragment extends Fragment {

    private String[] allActivities = {"Volleyball", "Drama"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    public SelectActivityFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectActivityFragment newInstance(String param1, String param2) {
        SelectActivityFragment fragment = new SelectActivityFragment();
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

        View view = inflater.inflate(R.layout.standard_list_layout, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.standard_recycle_view);

        RecyclerView.OnItemTouchListener touchListener = new StandardTouchListener(getContext(), recyclerView, new StandardTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();

                view.setActivated(true);

                if (allActivities[position].equals("Volleyball")) {

                    ActivitiesGradesFragment actGradeFragment = new ActivitiesGradesFragment();
                    actGradeFragment.activity = allActivities[position].toLowerCase().replaceAll("\\s+","");

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rootActivityView, actGradeFragment).commit();
                } else if (allActivities[position].equals("Drama")) {

                    SchoolActivityFragment schoolActivityFragment = new SchoolActivityFragment();

                    schoolActivityFragment.activity = "drama";
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rootActivityView, schoolActivityFragment).commit();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        });

        configureStandardRecyclerView(getContext(), recyclerView, allActivities, touchListener);

        AdViewModel adViewModel = ViewModelProviders.of(getActivity()).get(AdViewModel.class);

        adViewModel.getCurrentAd().observe(this, data -> changeAdView(view, data));

        // Inflate the layout for this fragment
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
}
