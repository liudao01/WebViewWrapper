package com.common.lib_model_web.fragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.lib_model_web.DWebView;
import com.common.lib_model_web.R;
import com.common.lib_model_web.WebConstants;

/**
 * Created by xud on 2017/12/16.
 */

public abstract class BaseWebviewFragment extends BaseFragment {


    protected DWebView webView;

    public String webUrl;

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            webUrl = bundle.getString(WebConstants.INTENT_TAG_URL);
            Log.d("TAG", "onCreate: webUrl = " + webUrl);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        webView = view.findViewById(R.id.web_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        webLifeCycle = new DefaultWebLifeCycleImpl(webView);
//        webView.registerdWebViewCallBack(this);
        loadUrl();
    }

    protected void loadUrl() {
        webView.loadUrl(webUrl);
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.dispatchEvent("pageResume");
//        webLifeCycle.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.dispatchEvent("pagePause");
//        webLifeCycle.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
//        webView.dispatchEvent("pageStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webView.dispatchEvent("pageDestroy");
//        webLifeCycle.onDestroy();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return onBackHandle();
        }
        return false;
    }

    protected boolean onBackHandle() {
        if (webView != null) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
