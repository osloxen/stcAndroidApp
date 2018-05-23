package com.locallinkonline.stcatherineschool.rest.tasks;

import com.locallinkonline.stcatherineschool.LunchFragment;
import com.locallinkonline.stcatherineschool.adapter.LunchDisplayAdapter;
import com.locallinkonline.stcatherineschool.rest.api.LunchApi;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import java.io.IOException;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

public class GetLunchDataTask extends SchoolDataTask<Date, Void, LunchResponseObject> {

    private final LunchFragment fragment;

    private final Lunch[] loadingLunch = {new Lunch(new Date(), false, "loading...")};

    public GetLunchDataTask(LunchFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void onPreExecute() {
        fragment.getMainListView().setAdapter(new LunchDisplayAdapter(fragment.getContext(), loadingLunch));
    }

    @Override
    protected LunchResponseObject doInBackground(Date... dates) {
        return getLunches(dates[0], dates[1]);
    }

    @Override
    protected void onPostExecute(LunchResponseObject response) {
        fragment.getMainListView().setAdapter(
                new LunchDisplayAdapter(fragment.getContext(),
                                        response.getLunchScheduleList().toArray(
                                                new Lunch[response.getLunchScheduleList().size()])));
    }

    private LunchResponseObject getLunches(Date startDate, Date endDate) {

        LunchApi lunchApi = retrofit.create(LunchApi.class);
        Call<LunchResponseObject> call = lunchApi.getLunches(sdf.format(startDate), sdf.format(endDate));

        Response<LunchResponseObject> response;

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
