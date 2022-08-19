package com.common.lib_model_web.fragment;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.lib_model_web.R;
import com.common.lib_model_web.WebConstants;

import wendu.dsbridge.DWebView;

/**
 * Created by xud on 2017/12/16.
 */

public abstract class BaseWebviewFragment extends BaseFragment {


    public WebView mWebview;

    public String webUrl;
    public View view;
    public Class clazz;
    private WebSettings settings;

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
        mWebview = view.findViewById(R.id.web_view);

        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWebView();
        loadUrl();

    }

    private void initWebView() {
        mWebview.requestFocusFromTouch();
        mWebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebview.setScrollbarFadingEnabled(false);

        settings = mWebview.getSettings();

        settings.setGeolocationEnabled(true);//定位
        settings.setAllowFileAccess(true);// 设置允许访问文件数据
        settings.setJavaScriptEnabled(true);// 设置支持javascript脚本
        mWebview.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebview.setVerticalScrollBarEnabled(false);//垂直不显示

        settings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点

        //若setSupportZoom是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
        settings.supportMultipleWindows();  //多窗口


        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setDefaultTextEncodingName("UTF-8");
        //这方法可以让你的页面适应手机屏幕的分辨率，完整的显示在屏幕上，可以放大缩小。
        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(false);
        //隐藏缩放按钮
        // 关于是否缩放
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            settings.setDisplayZoomControls(false);
        }

        //关闭硬件加速避免黑屏
//        mWebview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

//        webViewClient = new MyWebViewClient(mWebview);
        //第二种交互方式
//        registerHandler();


        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
// 设置支持缩放
        settings.setSupportZoom(true);
// 设置缩放工具的显示
        settings.setBuiltInZoomControls(true);////设置内置的缩放控件。
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小

        settings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小


        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片


        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//允许混合内容，5.0之后的api
        }
    }

    protected void loadUrl() {
        mWebview.loadUrl(webUrl);
//        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
//        webView.dispatchEvent("pageStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        webLifeCycle.onDestroy();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return onBackHandle();
        }
        return false;
    }

    protected boolean onBackHandle() {
        if (mWebview != null) {
            if (mWebview.canGoBack()) {
                mWebview.goBack();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
