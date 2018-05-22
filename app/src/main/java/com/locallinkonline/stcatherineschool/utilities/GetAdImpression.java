package com.locallinkonline.stcatherineschool.utilities;

import android.app.Fragment;
import android.os.AsyncTask;
import android.widget.TextView;

import com.locallinkonline.stcatherineschool.R;
import com.locallinkonline.stcatherineschool.rest.controller.GetAdImpressionController;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;

public class GetAdImpression extends AsyncTask<String,Void,AdUnit> {

    private final Fragment fragment;
    private final GetAdImpressionController adController = new GetAdImpressionController();

    public GetAdImpression(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected AdUnit doInBackground(String... params) {
        return adController.getAdImpression("android","1001","undefined");
    }

    @Override
    protected void onPostExecute(AdUnit result) {
        TextView adDisplay;
        adDisplay = this.fragment.getView().findViewById(R.id.localLinkAdBusiness);
        String adDisplayString = result != null ? result.getBusiness() + "\n" + result.getAdText() : "";
        adDisplay.setText(adDisplayString);
    }
}