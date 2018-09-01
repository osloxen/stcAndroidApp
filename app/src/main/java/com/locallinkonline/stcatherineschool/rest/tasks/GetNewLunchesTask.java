package com.locallinkonline.stcatherineschool.rest.tasks;

import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;
import com.locallinkonline.stcatherineschool.room.dao.LunchDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;

public class GetNewLunchesTask extends StCatherineTask {

    private final LunchDao dao;

    public GetNewLunchesTask(StCatherineDatabase db, String url) {
        super(db, url);
        this.dao = db.lunchDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        Call<LunchResponseObject> getLunchesCall = stCatherineApi.getLunches(sdf.format(new Date()));

        try {
            Response<LunchResponseObject> response = getLunchesCall.execute();
            LunchResponseObject lunches = response.body();

            if (lunches != null &&
                    lunches.getSchedule() != null &&
                    lunches.getSchedule().getLunches() != null &&
                    lunches.getSchedule().getLunches().length > 0) {
                dao.insert(lunches.getSchedule().getLunches());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}