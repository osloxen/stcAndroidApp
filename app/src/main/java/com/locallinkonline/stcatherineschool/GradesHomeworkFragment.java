package com.locallinkonline.stcatherineschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;





/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GradesHomeworkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GradesHomeworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradesHomeworkFragment extends android.app.Fragment {



    String[] mItems = { "Grade 8",
                        "Grade 7",
                        "Grade 6",
                        "Grade 5",
                        "Grade 4",
                        "Grade 3"
                        };




    ListView listView;
    ArrayAdapter<String> listViewAdapter;


    public String[] getColumnValues() {

        final List<String> columnNames = new ArrayList<String>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // All your networking logic
                // should be here

                // Create URL
                URL githubEndpoint = null;
                try {
                    //githubEndpoint = new URL("https://api.github.com/");
                    githubEndpoint = new URL("https://telbelahfa.execute-api.us-east-1.amazonaws.com/prod/stc/columns/grade6");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                // Create connection
                HttpsURLConnection myConnection;
                try {
                    myConnection = (HttpsURLConnection) githubEndpoint.openConnection();

                    if (myConnection.getResponseCode() == 200) {
                        // Success
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");

                        JsonReader jsonReader = new JsonReader(responseBodyReader);

                        jsonReader.beginObject(); // Start processing the JSON object
                        while (jsonReader.hasNext()) { // Loop through all keys

                            String key = jsonReader.nextName(); // Fetch the next key


                            List<String> columns = new ArrayList<String>();

                            if (key.equals("grade")) {
                                jsonReader.skipValue();
                            } else if (key.equals("numColumns")) {
                                jsonReader.skipValue();
                            } else if (key.equals("columnArray")) {
                                jsonReader.beginArray();
                                while (jsonReader.hasNext()) {
                                    columnNames.add(jsonReader.nextString());
                                }
                                jsonReader.endArray();
                            } else {
                                jsonReader.skipValue();
                            }

                            Log.d("getColumnVals:", Arrays.toString(columnNames.toArray()));
                        }

                        mItems = columnNames.toArray(new String[(columnNames.size())]);


                    } else {
                        // Error handling code goes here
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        return columnNames.toArray(new String[columnNames.size()]);
    }





    private OnFragmentInteractionListener mListener;

    public GradesHomeworkFragment() {
        // Required empty public constructor
    }



    public static GradesHomeworkFragment newInstance() {
        GradesHomeworkFragment fragment = new GradesHomeworkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grades_homework, container, false);

        listView = view.findViewById( R.id.homeworkGradesListView );

        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mItems
        );

        listView.setAdapter(listViewAdapter);

//        listView.invalidateViews();

//        new GetColumnData().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Toast.makeText(getActivity(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();


                HomeworkForGradeFragment homeworkFragment = new HomeworkForGradeFragment();
//                homeworkFragment.grade = "grade8";
                homeworkFragment.grade = mItems[position].toLowerCase().replaceAll("\\s+","");

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rootActivityView, homeworkFragment).commit();
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


    /**
     * Async private class below this comment should be last item in this class always.
     */


    private class GetColumnData extends AsyncTask<Void,Void,Void> {



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

                    GradesHomeworkFragment.this.getColumnValues();

                }

            });

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    mItems
            );

            Log.d("postExecute", Arrays.toString(mItems));

            GradesHomeworkFragment.this.listView.setAdapter(listViewAdapter);



        }

    }

}
