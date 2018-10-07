package com.locallinkonline.locallinkschool.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.locallinkonline.locallinkschool.R;

import androidx.fragment.app.Fragment;

public abstract class StandardTwitterFeedFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        WebView view = (WebView) inflater.inflate(R.layout.twitter_layout, container, false);
        view.setWebChromeClient(new WebChromeClient());
        view.setWebViewClient(new WebViewClient());
        view.getSettings().setAppCacheEnabled(true);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadDataWithBaseURL("https://twitter.com","<a class=\"twitter-timeline\" href=\"https://twitter.com/" + getUsername() + "?ref_src=twsrc%5Etfw\">Tweets by " + getUsername() + "</a> <script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>", "text/html", "utf-8","");

        return view;
    }

    protected abstract String getUsername();
}
