package com.locallinkonline.locallinkschool.util;

import android.view.View;
import android.widget.TextView;

import com.locallinkonline.locallinkschool.R;
import com.locallinkonline.locallinkschool.room.entity.AdEntity;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AdUtils {

    private static final AtomicInteger counter = new AtomicInteger();

    public static void changeAdView(View view, AdEntity[] ads) {
        TextView adBusiness = view.findViewById(R.id.localLinkAdBusiness);
        TextView adText = view.findViewById(R.id.localLinkAdText);
        if(ads != null && ads.length > 0) {
            AdEntity ad = ads[counter.getAndIncrement() % ads.length];
            adBusiness.setText(ad.getBusiness());
            adText.setText(ad.getAdText());
        }
    }
}
