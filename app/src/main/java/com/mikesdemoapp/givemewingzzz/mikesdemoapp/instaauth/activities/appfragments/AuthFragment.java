package com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.activities.appfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.mikesdemoapp.givemewingzzz.mikesdemoapp.R;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.listeners.GenericFinishListener;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.utils.AppConstants;
import com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.utils.CustomWebviewClient;

import static com.mikesdemoapp.givemewingzzz.mikesdemoapp.instaauth.utils.AppConstants.CLIENT_ID;


public class AuthFragment extends Fragment implements GenericFinishListener {

    private WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.web_view, container, false);

        CustomWebviewClient.addFinishListener(this);

        mWebView = (WebView) v.findViewById(R.id.insta_webView);
        mWebView.setWebViewClient(new CustomWebviewClient(getActivity()));
        mWebView.loadUrl(AppConstants.setAuthURL(CLIENT_ID));// If loading url

        return v;
    }

    @Override
    public void onFinishCallback() {

        getActivity().finish();

    }

}
