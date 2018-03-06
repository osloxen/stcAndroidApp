package com.locallinkonline.stcatherineschool.utilities;

import com.locallinkonline.stcatherineschool.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by dberge on 1/15/18.
 */

public class HandleRestCalls {

    public String getHomework(String grade, String startDate, String endDate) {

        String url = "https://telbelahfa.execute-api.us-east-1.amazonaws.com/prodblue/stc/homework/" +
                                                        grade +
                                                        "?" +
                                                        "startDate=" +
                                                        startDate +
                                                        "&endDate=" +
                                                        endDate;

        return getJSON(url, 10000);

    }

    public String getJSON(String url, int timeout) {
        HttpsURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpsURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
