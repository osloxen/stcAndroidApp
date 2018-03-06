package com.locallinkonline.stcatherineschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import com.locallinkonline.stcatherineschool.utilities.HandleRestCalls;
import com.locallinkonline.stcatherineschool.classrooms.GradeEight;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeworkForGradeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeworkForGradeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
    String[] initialCallForHomeworkValues = getHomeworkDataForGrade();
    ListView listView;
    ArrayAdapter<String> listViewAdapter;

    ArrayList<GradeEight> allHomework = new ArrayList<>();

    ArrayList<String> listOfDates = new ArrayList<>();

    HandleRestCalls getLocalLinkData = new HandleRestCalls();





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

    public class AllClassroomsGson {

        String alexaResponse;

        @SerializedName("homeworkArray")
        ArrayList<Map<String,String>> assignments;

        AllClassroomsGson() {
            assignments = new ArrayList<Map<String,String>>();
        }
    }

    public class GradeEightGson {

        String alexaResponse;

        @SerializedName("homeworkArray")
        List<GradeEight> assignments;

        GradeEightGson() {
            assignments = new ArrayList<GradeEight>();
        }
    }


    public String[] getHomeworkDataForGrade() {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here

                System.out.println("Processing this grade:  " + grade);

                HandleRestCalls getLocalLinkData = new HandleRestCalls();

                String json = getLocalLinkData.getHomework("grade8","2018-01-10","2018-01-15");

                Gson gson = new Gson();

                GradeEightGson mySpecialGson = gson.fromJson(json,GradeEightGson.class);
                AllClassroomsGson getDatesGson = gson.fromJson(json,AllClassroomsGson.class);

                populateListOfDates(getDatesGson.assignments);






                GsonBuilder builder = new GsonBuilder();


                Object o = builder.create().fromJson(json, Object.class);

                LinkedTreeMap<String, Object> jsonMap = new LinkedTreeMap<>();
                jsonMap = (LinkedTreeMap) o;

                ArrayList<LinkedTreeMap> arrayOfHomework = new ArrayList<>();

                for(Map.Entry<String,Object> entry : jsonMap.entrySet()) {

                    String key = entry.getKey();

                    if (key.equals("homeworkArray")) {
                        System.out.println("FOUND THE HOMEWORK ARRAY");
                        arrayOfHomework = (ArrayList<LinkedTreeMap>) entry.getValue();
                    }

                    Object value = entry.getValue();

                    System.out.println(key + " => " + value);
                }

                System.out.println("*** ARRAY VALUES BELOW ***");

                for (Object obj: arrayOfHomework){

                    // IS THIS THE RIGHT PLACE TO MAKE THE NEW OBJECT?
                    GradeEight gradeEight = new GradeEight();

                    System.out.println(" => " + obj);

                    LinkedTreeMap<String,String> dayTreeMap = new LinkedTreeMap<>();

                    dayTreeMap = (LinkedTreeMap) obj;

                    for(Map.Entry<String,String> entry : dayTreeMap.entrySet()) {

                        String key = entry.getKey();

                        String value = entry.getValue();

                        switch(key)
                        {
                            case "date":
                                System.out.println("FOUND DATE");
                                gradeEight.date = value;
                                String humanReadableDate = convertDateToHumanReadable(value);
                                //listOfDates.add(humanReadableDate);
                                break;
                            case "general reminder":
                                gradeEight.genReminder = value;
                                break;
                            case "algebra":
                                gradeEight.algebra = value;
                                break;
                            case "algebra fundamentals":
                                gradeEight.algebraFund = value;
                                break;
                            case "english":
                                gradeEight.english = value;
                                break;
                            case "middle school math":
                                gradeEight.midSchoolMath = value;
                                break;
                            case "social studies":
                                gradeEight.socialStudies = value;
                                break;
                            case "spanish":
                                gradeEight.spanish = value;
                                break;
                            case "science":
                                gradeEight.science = value;
                                break;
                            case "music":
                                gradeEight.music = value;
                                break;
                            case "current class project":
                                gradeEight.classProject = value;
                                break;
                            case "next special event":
                                gradeEight.specialEvent = value;
                                break;
                            default:
                                System.out.println("ERROR!  UNKNOWN KEY! ");
                        }

                        System.out.println("Array values " + key + " => " + value);
                    }

                    allHomework.add(gradeEight);

                    System.out.println("gradeEight object should have values" );

                }
            }
        });

        String[] returnLoadingIfNull = {"Loading..."};


        if (listOfDates == null) {
            return returnLoadingIfNull;
        } else {
            return listOfDates.toArray(new String[listOfDates.size()]);
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
        View view = inflater.inflate(R.layout.fragment_homework_for_grade, container, false);

        listView = view.findViewById( R.id.homeworkByGrade );

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                listOfDates.toArray(new String[listOfDates.size()])
        );

        listView.setAdapter(listViewAdapter);

        new GetHomeworkData(listViewAdapter).execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getActivity(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();


                SubjectHomeworkFragment subjectFrag = new SubjectHomeworkFragment();
                subjectFrag.allHomework = allHomework;
                subjectFrag.position = position;

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

        ArrayAdapter<String> listViewAdapter;


        GetHomeworkData(ArrayAdapter<String> adapter){
            this.listViewAdapter = adapter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("preExecute", Arrays.toString(mItems));

        }


        @Override
        protected Void doInBackground(Void... voids) {

            final List<String> columnNames = new ArrayList<String>();


            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {

                    HomeworkForGradeFragment.this.getHomeworkDataForGrade();

                }

            });

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // ***********
            // If there is more than one element then we can remove the first one which is added as "Loading..."
            // ***********
            if (listOfDates.size() > 1) {
                listOfDates.remove(0);
            }

            listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    listOfDates.toArray(new String[listOfDates.size()])
            );


            Log.d("postExecute", Arrays.toString(mItems));

            HomeworkForGradeFragment.this.listView.setAdapter(listViewAdapter);
        }
    }
}
