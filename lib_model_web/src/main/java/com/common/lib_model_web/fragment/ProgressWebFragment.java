package com.common.lib_model_web.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.common.lib_model_web.BuildConfig;
import com.common.lib_model_web.DWebView;
import com.common.lib_model_web.R;
import com.common.lib_model_web.WebConstants;
import com.common.lib_model_web.brige.JavaScriptInterfaceApi;
import com.common.lib_model_web.interfaces.DWebViewCallBack;

import java.io.Serializable;

/**
 * Created by xud on 2017/12/16.
 */

public class ProgressWebFragment extends BaseWebviewFragment {
    private ProgressBar pbLoad;
    public JavaScriptInterfaceApi api;

    public static ProgressWebFragment newInstance(String keyUrl) {
        ProgressWebFragment fragment = new ProgressWebFragment();
        fragment.setArguments(getBundle(keyUrl));
        return fragment;
    }


    public static ProgressWebFragment newInstance(String keyUrl, Class<? extends Serializable> classz) {
        ProgressWebFragment fragment = new ProgressWebFragment();
        fragment.setArguments(getBundle(keyUrl, classz));
        return fragment;
    }

    public static Bundle getBundle(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.INTENT_TAG_URL, url);
        return bundle;
    }

    public static Bundle getBundle(String url, Class<?> classz) {
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.INTENT_TAG_URL, url);
        bundle.putSerializable(WebConstants.INTENT_TAG_CLAZZ, classz);
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

        if (BuildConfig.DEBUG) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webView.setWebContentsDebuggingEnabled(true);
            }
        }

        api = new JavaScriptInterfaceApi(mContext, webView);


    }

    public JavaScriptInterfaceApi getApi(){
        return api;
    }

    public void jsMethods() {
        api.executeJavascript("androidToJs(333)", new JavaScriptInterfaceApi.JavascriptCallback() {
            @Override
            public void onReceiveValue(String value) {
                Log.d("jsMethods", "onReceiveValue: " + value);
            }
        });

    }


}
