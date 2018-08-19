package com.locallinkonline.locallinkschool.util;

import android.view.View;
import android.widget.TextView;

import com.locallinkonline.locallinkschool.R;
import com.locallinkonline.locallinkschool.room.entity.AdEntity;

public class AdUtils {

    public static void changeAdView(View view, AdEntity data) {
        TextView adBusiness = view.findViewById(R.id.localLinkAdBusiness);
        TextView adText = view.findViewById(R.id.localLinkAdText);
        if(data != null) {
            adBusiness.setText(data.getBusiness());
            adText.setText(data.getAdText());
        }
    }
}
