package com.locallinkonline.stcatherineschool.rest.tasks;

import com.locallinkonline.stcatherineschool.rest.model.Schedule;
import com.locallinkonline.stcatherineschool.room.dao.SchoolScheduleDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;
import com.locallinkonline.stcatherineschool.room.entity.ScheduleEntity;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetNewScheduleTask extends StCatherineTask<String> {

    SchoolScheduleDao dao;

    public GetNewScheduleTask(StCatherineDatabase db, String url) {
        super(db, url);
        this.dao = db.schoolScheduleDao();
    }

    @Override
    protected Void doInBackground(String... args) {

        String identifier = args != null && args.length > 0 && args[0] != null ? args[0] : "school-schedule";
        String type = args != null && args.length > 0 ? args[1] : null;

        Call<Schedule> call;

        if("Homework".equals(type)) {
            call = stCatherineApi.getHomeworkSchedule(identifier);
        } else if ("Sports".equals(type)) {
            call = stCatherineApi.getActivitySchedule(identifier);
        } else {
            call = stCatherineApi.getSchoolSchedule();
        }

        try {
            Response<Schedule> response = call.execute();

            List<ScheduleEntity> entities = response.body().getSchedule();

            for(int i = 0; i < entities.size(); i++){
                entities.get(i).setScheduleType(identifier);
            }

            if(entities.size() > 0) {
                dao.insert(entities);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
