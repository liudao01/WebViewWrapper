package com.common.lib_model_web.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.lib_model_web.DWebView;
import com.common.lib_model_web.R;
import com.common.lib_model_web.WebConstants;
import com.common.lib_model_web.brige.JSBridge;

/**
 * Created by xud on 2017/12/16.
 */

public abstract class BaseWebviewFragment extends BaseFragment {


    protected DWebView webView;

    public String webUrl;
    public View view;
    public Class clazz;

    @LayoutRes
    protected abstract int getLayoutRes();

    public void initView() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            webUrl = bundle.getString(WebConstants.INTENT_TAG_URL);
            clazz = (Class) bundle.getSerializable(WebConstants.INTENT_TAG_CLAZZ);
            Log.d("TAG", "onCreate: webUrl = " + webUrl);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutRes(), container, false);
        webView = view.findViewById(R.id.web_view);

        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        webLifeCycle = new DefaultWebLifeCycleImpl(webView);
//        webView.registerdWebViewCallBack(this);
        loadUrl();

        if (clazz != null) {
            JSBridge.register("JSBridge", clazz);
        }
        webView.addJavascriptInterface(new JSBridge(webView), "_jsbridge");
    }

    protected void loadUrl() {
        webView.loadUrl(webUrl);
//        webView.loadUrl("file:///android_asset/index.html");
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
