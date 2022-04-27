package com.common.lib_model_web.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.lib_model_web.R;
import com.common.lib_model_web.WebConstants;
import com.common.lib_model_web.interfaces.DWebViewCallBack;

/**
 * Created by xud on 2017/12/16.
 */

public class ProgressWebFragment extends BaseWebviewFragment {
    private ProgressBar pbLoad;

    public static ProgressWebFragment newInstance(String keyUrl) {
        ProgressWebFragment fragment = new ProgressWebFragment();
        fragment.setArguments(getBundle(keyUrl));
        return fragment;
    }

    public static Bundle getBundle(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.INTENT_TAG_URL, url);
        return bundle;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_webview;
    }

    public void initView() {
        pbLoad = (ProgressBar) view.findViewById(R.id.pb_load);
        pbLoad.setProgress(100);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                pbLoad.setProgress(progress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        webView.registerdWebViewCallBack(new DWebViewCallBack() {
            @Override
            public void pageStarted(String url) {
                pbLoad.setVisibility(View.VISIBLE);
            }

            @Override
            public void pageFinished(String url) {
                pbLoad.setVisibility(View.GONE);
            }

            @Override
            public boolean overrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onError() {

            }

        });


    }

}
