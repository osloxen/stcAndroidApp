package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AdUnit {
    @SerializedName("businessId")
    private String businessId;
    @SerializedName("business")
    private String business;
    @SerializedName("adTitle")
    private String adTitle;
    @SerializedName("adText")
    private String adText;

    public AdUnit waitForAdUnitToLoad() {
        AdUnit waitForAdToLoad = new AdUnit();
        waitForAdToLoad.business = "loading...";
        waitForAdToLoad.adText = "loading...";

        return waitForAdToLoad;
    }
}
