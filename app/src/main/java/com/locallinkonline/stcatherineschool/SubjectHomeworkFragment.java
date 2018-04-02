package com.locallinkonline.stcatherineschool;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.locallinkonline.stcatherineschool.adapter.HomeworkSubjectDisplayAdapter;
import com.locallinkonline.stcatherineschool.classrooms.GradeEight;
import com.locallinkonline.stcatherineschool.rest.controller.GetAdImpressionController;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import com.locallinkonline.stcatherineschool.rest.model.HomeworkClassAllGrades;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubjectHomeworkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubjectHomeworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubjectHomeworkFragment extends android.app.Fragment {

    String[] arrayForListView = {"Loading..."};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Passed in from HomeworkForGradeFragment
    public ArrayList<GradeEight> allHomework = new ArrayList<>();
    public Integer position;

    ListView listView;
    ArrayAdapter<String> listViewAdapter;

    List<HomeworkClassAllGrades> gradeSchoolHomeworkList;
    List<HomeworkClassAllGrades> middleSchoolHomeworkList;

    String grade;

    View view;

    AdUnit adToDisplay;

    public SubjectHomeworkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubjectHomeworkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubjectHomeworkFragment newInstance(String param1, String param2) {
        SubjectHomeworkFragment fragment = new SubjectHomeworkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_subject_homework, container, false);

        // subjectsList
//        listView = view.findViewById( R.id.subjectsList );
        listView = view.findViewById( R.id.homeworkByGrade);

        if (grade.equals("grade8")) {

            HomeworkClassAllGrades homeworkToPresent = middleSchoolHomeworkList.get(position);

            arrayForListView = homeworkToPresent.getEigthGradeHomeworkAsArray();


        } else if (grade.equals("grade7")) {

            HomeworkClassAllGrades homeworkToPresent = middleSchoolHomeworkList.get(position);

            arrayForListView = homeworkToPresent.getSeventhGradeHomeworkAsArray();

        } else if (grade.equals("grade6")) {

            HomeworkClassAllGrades homeworkToPresent = middleSchoolHomeworkList.get(position);

            arrayForListView = homeworkToPresent.getSixthGradeHomeworkAsArray();

        } else {


            HomeworkClassAllGrades homeworkToPresent = gradeSchoolHomeworkList.get(position);

            arrayForListView = homeworkToPresent.getAllGradeschoolHomeworkAsArray();
        }

        new GetAdImpression().execute();

        HomeworkSubjectDisplayAdapter homeworkListAdapter = new HomeworkSubjectDisplayAdapter(this.getContext(),arrayForListView);

/*
        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                arrayForListView
        );
*/


//        listView.setAdapter(listViewAdapter);
        listView.setAdapter(homeworkListAdapter);



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

    private SubjectHomeworkFragment.OnFragmentInteractionListener mListener;


    public class GetAdImpression  extends AsyncTask<Void,Void,Void> {

        AdUnit ad;

        @Override
        protected Void doInBackground(Void... params) {


            System.out.println("THIS IS ASYNC WORKING in Get Ad Impression!!!");


            GetAdImpressionController adController = new GetAdImpressionController();
            ad = adController.getAdImpression("android","1001","undefined");

            return null;
        }



        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


            SubjectHomeworkFragment.this.adToDisplay = ad;

            TextView adDisplay;

            adDisplay = SubjectHomeworkFragment.this.view.findViewById(R.id.homeworkLocalLinkAd);

            String adDisplayString = ad.getBusiness() + "\n" + ad.getAdText();

            adDisplay.setText(adDisplayString);
        }
    }
}
