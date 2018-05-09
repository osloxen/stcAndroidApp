package com.locallinkonline.stcatherineschool.rest.controller;

import com.locallinkonline.stcatherineschool.rest.api.AdEngineApi;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class GetAdImpressionController extends BaseAdEngineController {

    public AdUnit getAdImpression(String platform, String schoolId, String impressionLocation) {

        AdEngineApi adEngineApi = retrofit.create(AdEngineApi.class);
        Call<AdUnit> call = adEngineApi.getAd(platform,  schoolId,  impressionLocation);

        Response<AdUnit> response;

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
