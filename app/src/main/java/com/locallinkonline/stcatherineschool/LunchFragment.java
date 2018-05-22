package com.locallinkonline.stcatherineschool;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.locallinkonline.stcatherineschool.adapter.LunchDisplayAdapter;
import com.locallinkonline.stcatherineschool.rest.controller.LunchController;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
import com.locallinkonline.stcatherineschool.utilities.GetAdImpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LunchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LunchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LunchFragment extends android.app.Fragment {

    private final GetAdImpression adImpressionRetriever;

    String[] mItems = {"Loading..."};
    String[] allLunches = {"Loading..."};

    AdUnit adToDisplay;

    View view;

    Lunch loadingLunchObject = new Lunch();
    Lunch[] lunches = {loadingLunchObject.waitForLunchToLoad()};

    //List<Lunch> allLunches = new List<Lunch>();
    ArrayList<String> listOfLunches = new ArrayList<>();

    ListView listView;
    ArrayAdapter<String> listViewAdapter;

    LunchDisplayAdapter lunchViewAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LunchFragment() { this.adImpressionRetriever = new GetAdImpression(this); }

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lunch, container, false);

        listView = view.findViewById(R.id.lunchDetailsListView);

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                allLunches
        );

        lunchViewAdapter = new LunchDisplayAdapter(this.getContext(),lunches);

//        listView.setAdapter(listViewAdapter);
        listView.setAdapter(lunchViewAdapter);

        adImpressionRetriever.execute();

        new GetLunchData().execute();

//        listView.invalidateViews();


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



    private class GetLunchData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {


            System.out.println("THIS IS ASYNC WORKING!!!");

            // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date startDate = new Date();
            long ltime=startDate.getTime()+8*24*60*60*1000;
            Date endDate=new Date(ltime);


            LunchController lunchController = new LunchController();
            LunchResponseObject lunchFromCloud = lunchController.getLunches(startDate,endDate);
            List<Lunch> lunchList = lunchFromCloud.getLunchScheduleList();
            List<String> updateLunchesWithThisArray = new ArrayList<>();

            for(Lunch lunch : lunchList) {

                Log.d("Lunch Description: ", lunch.getDescription());
                updateLunchesWithThisArray.add(lunch.getDescription());
            }

            LunchFragment.this.allLunches = updateLunchesWithThisArray.toArray(new String[updateLunchesWithThisArray.size()]);
            LunchFragment.this.lunches = lunchList.toArray(new Lunch[lunchList.size()]);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

              listViewAdapter = new ArrayAdapter<String>(
                      getActivity(),
                      android.R.layout.simple_list_item_1,
                      LunchFragment.this.allLunches
              );

            lunchViewAdapter = new LunchDisplayAdapter(LunchFragment.this.getContext(),lunches);

            Log.d("postExecute", Arrays.toString(LunchFragment.this.allLunches));

            listView.setAdapter(lunchViewAdapter);



        }
    }
}
