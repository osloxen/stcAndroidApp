package com.locallinkonline.stcatherineschool.rest.tasks;

import com.locallinkonline.stcatherineschool.rest.model.SchoolSchedule;
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;
import com.locallinkonline.stcatherineschool.room.entity.SchoolScheduleEntity;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetNewScheduleTask extends StCatherineTask {

    SchoolScheduleDao dao;

    public GetNewScheduleTask(StCatherineDatabase db, String url) {
        super(db, url);
        this.dao = db.schoolScheduleDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Call<SchoolSchedule> call = stCatherineApi.getSchoolSchedule();
        try {
            Response<SchoolSchedule> response = call.execute();

            List<SchoolScheduleEntity> entities = response.body().getSchedule();

            if(entities != null && entities.size() > 0) {
                dao.insert(entities);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
