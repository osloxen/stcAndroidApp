package com.locallinkonline.stcatherineschool.util;

import android.view.View;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.room.entity.AdEntity;

import java.util.List;

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
