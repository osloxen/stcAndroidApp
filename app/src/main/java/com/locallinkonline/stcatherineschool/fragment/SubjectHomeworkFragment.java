package com.locallinkonline.stcatherineschool.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.adapter.HomeworkSubjectDisplayAdapter;
import com.locallinkonline.stcatherineschool.classrooms.GradeEight;
import com.locallinkonline.stcatherineschool.rest.model.HomeworkClassAllGrades;
import com.locallinkonline.locallinkschool.view.AdViewModel;


import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static com.locallinkonline.locallinkschool.util.AdUtils.changeAdView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubjectHomeworkFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubjectHomeworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubjectHomeworkFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Passed in from HomeworkForGradeFragment
    public ArrayList<GradeEight> allHomework = new ArrayList<>();
    public Integer position;

    ArrayAdapter<String> listViewAdapter;

    List<HomeworkClassAllGrades> gradeSchoolHomeworkList;
    List<HomeworkClassAllGrades> middleSchoolHomeworkList;

    String grade;

    public SubjectHomeworkFragment() { }

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
        View view = inflater.inflate(R.layout.fragment_subject_homework, container, false);

        // subjectsList
//        listView = view.findViewById( R.id.subjectsList );
        ListView listView = view.findViewById(R.id.homeworkByGrade);

        String[] arrayForListView;
        switch (grade) {
            case "grade8": {

                HomeworkClassAllGrades homeworkToPresent = middleSchoolHomeworkList.get(position);

                arrayForListView = homeworkToPresent.getEigthGradeHomeworkAsArray();

                break;
            }
            case "grade7": {

                HomeworkClassAllGrades homeworkToPresent = middleSchoolHomeworkList.get(position);

                arrayForListView = homeworkToPresent.getSeventhGradeHomeworkAsArray();

                break;
            }
            case "grade6": {

                HomeworkClassAllGrades homeworkToPresent = middleSchoolHomeworkList.get(position);

                arrayForListView = homeworkToPresent.getSixthGradeHomeworkAsArray();

                break;
            }
            default: {


                HomeworkClassAllGrades homeworkToPresent = gradeSchoolHomeworkList.get(position);

                arrayForListView = homeworkToPresent.getAllGradeschoolHomeworkAsArray();
                break;
            }
        }

        AdViewModel adViewModel = ViewModelProviders.of(getActivity()).get(AdViewModel.class);

        adViewModel.getData().observe(this, data -> {
            changeAdView(view, data);
        });

        HomeworkSubjectDisplayAdapter homeworkListAdapter = new HomeworkSubjectDisplayAdapter(this.getContext(), arrayForListView);

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
}
