package com.locallinkonline.stcatherineschool.rest.tasks;

import com.locallinkonline.stcatherineschool.fragment.SchoolActivityFragment;
import com.locallinkonline.stcatherineschool.rest.api.ActivityApi;
import com.locallinkonline.stcatherineschool.rest.model.Activities;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class GetSchoolActivityTask extends SchoolDataTask<String, Void, Activities> {

    private static final int NOTES_INDEX = 3;
    public static final int ANNOUNCEMENT_INDEX = 1;
    private static final int REGULAR_SCHEDULE_INDEX = 5;
    private static final int PERFORMANCE_INDEX = 7;
    public static final String LOADING = "Loading...";

    private final SchoolActivityFragment fragment;

    public GetSchoolActivityTask(SchoolActivityFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void onPreExecute() {
        fragment.getNotesDataView().setText(LOADING);
    }

    @Override
    protected Activities doInBackground(String... strings) {
        return getActivityNotifications(strings[0]);
    }

    @Override
    protected void onPostExecute(Activities result) {
        fragment.getNotesDataView().setText(result.getNotifications()[NOTES_INDEX]);
        fragment.getAnnouncementDataView().setText(result.getNotifications()[ANNOUNCEMENT_INDEX]);
        fragment.getRegularScheduleDataView().setText(result.getNotifications()[REGULAR_SCHEDULE_INDEX]);
        fragment.getPerformanceDataView().setText(result.getNotifications()[PERFORMANCE_INDEX]);
    }

    private Activities getActivityNotifications(String activity) {

        // TODO FIX EVERYTHING BELOW THIS!!!
        ActivityApi activityApi = retrofit.create(ActivityApi.class);
        Call<Activities> call = activityApi.getActivityNotifications(activity);

        Response<Activities> response;

        try {
            response = call.execute();


        } catch (IOException e) {
            response = null;
        }

        if(response != null && response.isSuccessful()) {
            return response.body();
        }

        return null;
    }

}
