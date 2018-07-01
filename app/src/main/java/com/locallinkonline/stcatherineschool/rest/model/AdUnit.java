package com.locallinkonline.stcatherineschool.rest.model;

import com.google.gson.annotations.SerializedName;

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

    public String getDisplayString() {
        return this.getBusiness() + "\n" + this.getAdText();
    }
}
