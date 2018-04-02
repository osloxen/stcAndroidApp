package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;



public class AdUnit {
    @SerializedName("businessId")
    private String businessId;
    @SerializedName("business")
    private String business;
    @SerializedName("adTitle")
    private String adTitle;
    @SerializedName("adText")
    private String adText;

    public AdUnit() {}

    public String getBusiness() {
        return business;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public String getAdText() {
        return adText;
    }

    public AdUnit waitForAdUnitToLoad() {
        AdUnit waitForAdToLoad = new AdUnit();
        waitForAdToLoad.business = "loading...";
        waitForAdToLoad.adText = "loading...";

        return waitForAdToLoad;
    }
}
