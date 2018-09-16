package com.locallinkonline.stcatherineschool.rest.tasks;

import com.locallinkonline.stcatherineschool.rest.model.DataResourceResponse;
import com.locallinkonline.stcatherineschool.room.dao.DataResourcesDao;
import com.locallinkonline.stcatherineschool.room.db.StCatherineDatabase;
import com.locallinkonline.stcatherineschool.room.entity.MenuEntity;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GetNewDataResourcesTask extends StCatherineTask {

    private DataResourcesDao dataResourcesDao;

    public GetNewDataResourcesTask(StCatherineDatabase db, String url) {
        super(db, url);
        this.dataResourcesDao = db.dataResourcesDao();
    }

    @Override
    protected Void doInBackground(String... args) {
        Call<DataResourceResponse> dataResourceResponseCall = stCatherineApi.getDataResources();

        try
        {
            Response<DataResourceResponse> response = dataResourceResponseCall.execute();
            List<MenuEntity> menuEntities = response.body() != null ? response.body().getResourceList() : Collections.EMPTY_LIST;
            if(menuEntities.size() > 0) {
                dataResourcesDao.updateMenuItems(menuEntities);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
