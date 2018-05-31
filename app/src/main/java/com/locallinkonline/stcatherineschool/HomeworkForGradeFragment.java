package com.locallinkonline.stcatherineschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import com.locallinkonline.stcatherineschool.rest.controller.GradeschoolHomeworkController;
import com.locallinkonline.stcatherineschool.rest.controller.HomeworkController;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import com.locallinkonline.stcatherineschool.rest.model.EighthGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.GradeschoolHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.HomeworkClassAllGrades;
import com.locallinkonline.stcatherineschool.rest.model.SeventhGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.rest.model.SixthGradeHomeworkSchedule;
import com.locallinkonline.stcatherineschool.classrooms.GradeEight;

public class HomeworkForGradeFragment extends android.app.Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_GRADE = "param1";

    // TODO: Rename and change types of parameters
    public  String grade;

    public void setGrade(String gradeFromListView) {
        grade = gradeFromListView;
    }

    String[] mItems = {"Loading..."};
 //   String[] initialCallForHomeworkValues = getHomeworkDataForGrade();
    ListView listView;
    ArrayAdapter<String> listViewAdapter;

    ArrayList<GradeEight> allHomework = new ArrayList<>();

    ArrayList<String> listOfDates = new ArrayList<>();

    List<HomeworkClassAllGrades> gradeSchoolHomeworkList;
    List<HomeworkClassAllGrades> middleSchoolHomeworkList;

    AdUnit adToDisplay;

    View view;

    public String convertDateToHumanReadable(String originalDate) {

        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat targetFormat = new SimpleDateFormat("EEE, MMM d");

        String formattedDate = originalDate;

        try {
            Date date = originalFormat.parse(originalDate);
            formattedDate = targetFormat.format(date);
        } catch (Exception e) {
            Log.d("EXCEPTION", e.toString());
            Log.d("Error","FORMATTING DID NOT WORK");
        }

        return formattedDate;
    }

    public void populateListOfDates(ArrayList<Map<String,String>> assignments) {

        for (Map<String,String> assignmentDayMap : assignments) {

            for (Map.Entry<String,String> entry : assignmentDayMap.entrySet()) {

                String key = entry.getKey();

                if (key.equals("date")) {
                    System.out.println("FOUND THE DATE!!! ");
                    String humanReadableDate = convertDateToHumanReadable(entry.getValue());
                    listOfDates.add(humanReadableDate);
                }
                System.out.println("examined key => " + key);
            }

        }
    }

    private OnFragmentInteractionListener mListener;

    public HomeworkForGradeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param grade Parameter 1.
     * @return A new instance of fragment HomeworkForGradeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeworkForGradeFragment newInstance(String grade) {
        HomeworkForGradeFragment fragment = new HomeworkForGradeFragment();
        Bundle args = new Bundle();

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

        Log.d("grade",grade);

        listOfDates.add("Loading...");

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_homework_for_grade, container, false);

        listView = view.findViewById( R.id.homeworkByGrade );

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                listOfDates.toArray(new String[listOfDates.size()])
        );

        listView.setAdapter(listViewAdapter);

        new GetHomeworkData().execute();

        //new GetAdImpression().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                SubjectHomeworkFragment subjectFrag = new SubjectHomeworkFragment();

                if (grade.equals("grade8") || grade.equals("grade7") || grade.equals("grade6")) {

                    subjectFrag.middleSchoolHomeworkList = middleSchoolHomeworkList;

                } else {

                    subjectFrag.gradeSchoolHomeworkList = gradeSchoolHomeworkList;
                }

                subjectFrag.allHomework = allHomework;
                subjectFrag.position = position;
                subjectFrag.grade = grade;

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rootActivityView, subjectFrag).commit();
            }
        });

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

    private class GetHomeworkData extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            System.out.println("THIS IS ASYNC WORKING!!!");

            DateFormat dateFormat = new SimpleDateFormat("EEEE MMM dd");
            Date startDate = new Date();
            /*
            long ltime=startDate.getTime()+8*24*60*60*1000;
            Date endDate=new Date(ltime);
            */

            Calendar c=new GregorianCalendar();
            c.add(Calendar.DATE, 30);
            Date endDate = c.getTime();

            if (grade.equals("grade8")) {

                HomeworkController homeworkController = new HomeworkController();
                EighthGradeHomeworkSchedule homeworkFromCloud = homeworkController.getHomeworkEigthGrade(startDate,endDate);

                if(homeworkFromCloud != null) {
                    List<HomeworkClassAllGrades> homeworkList = homeworkFromCloud.getHomeworkList();
                    List<String> addDatesToThisArray = new ArrayList<>();

                    for(HomeworkClassAllGrades homework : homeworkList) {

                        Log.d("Date: ", homework.getDate().toString());
                        addDatesToThisArray.add(dateFormat.format(homework.getDate()));
                    }

                    HomeworkForGradeFragment.this.middleSchoolHomeworkList = homeworkList;
                    HomeworkForGradeFragment.this.mItems = addDatesToThisArray.toArray(new String[addDatesToThisArray.size()]);
                }

            } else if (grade.equals("grade7")) {

                HomeworkController homeworkController = new HomeworkController();
                SeventhGradeHomeworkSchedule homeworkFromCloud = homeworkController.getHomeworkSeventhGrade(startDate,endDate);

                if(homeworkFromCloud != null) {
                    List<HomeworkClassAllGrades> homeworkList = homeworkFromCloud.getHomeworkList();
                    List<String> addDatesToThisArray = new ArrayList<>();

                    for (HomeworkClassAllGrades homework : homeworkList) {

                        Log.d("Date: ", homework.getDate().toString());
                        addDatesToThisArray.add(dateFormat.format(homework.getDate()));
                    }

                    HomeworkForGradeFragment.this.middleSchoolHomeworkList = homeworkList;
                    HomeworkForGradeFragment.this.mItems = addDatesToThisArray.toArray(new String[addDatesToThisArray.size()]);
                }

            } else if (grade.equals("grade6")) {

                HomeworkController homeworkController = new HomeworkController();
                SixthGradeHomeworkSchedule homeworkFromCloud = homeworkController.getHomeworkSixthGrade(startDate,endDate);

                if(homeworkFromCloud != null) {
                    List<HomeworkClassAllGrades> homeworkList = homeworkFromCloud.getHomeworkList();
                    List<String> addDatesToThisArray = new ArrayList<>();

                    for (HomeworkClassAllGrades homework : homeworkList) {

                        Log.d("Date: ", homework.getDate().toString());
                        addDatesToThisArray.add(dateFormat.format(homework.getDate()));
                    }

                    HomeworkForGradeFragment.this.middleSchoolHomeworkList = homeworkList;
                    HomeworkForGradeFragment.this.mItems = addDatesToThisArray.toArray(new String[addDatesToThisArray.size()]);
                }

            } else {

                GradeschoolHomeworkController homeworkController = new GradeschoolHomeworkController();
                GradeschoolHomeworkSchedule homeworkFromCloud = homeworkController.getHomework(grade, startDate,endDate);

                if(homeworkFromCloud != null) {
                    List<HomeworkClassAllGrades> homeworkList = homeworkFromCloud.getHomeworkList();
                    List<String> addDatesToThisArray = new ArrayList<>();

                    for (HomeworkClassAllGrades homework : homeworkList) {

                        Log.d("Date: ", homework.getDate().toString());
                        addDatesToThisArray.add(dateFormat.format(homework.getDate()));
                    }

                    HomeworkForGradeFragment.this.gradeSchoolHomeworkList = homeworkList;
                    HomeworkForGradeFragment.this.mItems = addDatesToThisArray.toArray(new String[addDatesToThisArray.size()]);
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            Log.d("postExecute", Arrays.toString(HomeworkForGradeFragment.this.mItems));

            listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    HomeworkForGradeFragment.this.mItems
            );

            HomeworkForGradeFragment.this.listView.setAdapter(listViewAdapter);
        }
    }
}
