package com.locallinkonline.stcatherineschool.utilities;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.locallinkonline.stcatherineschool.LunchFragment;
import com.locallinkonline.stcatherineschool.adapter.LunchDisplayAdapter;
import com.locallinkonline.stcatherineschool.rest.controller.GetAdImpressionController;
import com.locallinkonline.stcatherineschool.rest.controller.LunchController;
import com.locallinkonline.stcatherineschool.rest.model.AdUnit;
import com.locallinkonline.stcatherineschool.rest.model.Lunch;
import com.locallinkonline.stcatherineschool.rest.model.LunchResponseObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by dberge on 3/27/18.
 */

public class GetAdImpression  extends AsyncTask<Void,Void,Void> {

    AdUnit ad;

    @Override
    protected Void doInBackground(Void... params) {


        System.out.println("THIS IS ASYNC WORKING in Get Ad Impression!!!");


        GetAdImpressionController adController = new GetAdImpressionController();
        ad = adController.getAdImpression("android","1001","undefined");

        return null;
    }


    public AdUnit getAd() {
        return ad;
    }

/*
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);


        listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                LunchFragment.this.allLunches
        );

        lunchViewAdapter = new LunchDisplayAdapter(LunchFragment.this.getContext(),lunches);

        Log.d("postExecute", Arrays.toString(LunchFragment.this.allLunches));

        listView.setAdapter(lunchViewAdapter);

    }

*/

}
