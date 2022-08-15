package com.madchan.webviewdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CommonWebViewActivity extends Activity {

    //    public static final String TEST_URL = "http://blog.csdn.net/liudao7994?skin=dark1";
    public static final String TEST_URL = "http://10.26.2.109:8080/pos-html/login?v=6384223247";
    private static final String INTENT_EXTRA_URL = "INTENT_EXTRA_URL";
    private String url;

    private TextView tvTitle;
    private ProgressBar pbLoad;
    private WebView wvCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);
        initData();
        initView();
    }

    private void initData() {
        url = getIntent().getStringExtra(INTENT_EXTRA_URL);
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        pbLoad = (ProgressBar) findViewById(R.id.pb_load);
        pbLoad.setProgress(100);
        initWebView();
    }

    private void initWebView() {
        wvCommon = (WebView) findViewById(R.id.wv_common);
        WebSettings settings = wvCommon.getSettings();
        WebSettings webSettings = wvCommon.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//         当网页需要保存数时据,设置下面属性
        webSettings.setDomStorageEnabled(true);

        //设置页面自适应 窗口大小改变后页面自适应改变
        webSettings.setUseWideViewPort(true);//设置为使用webview推荐的窗口，主要是为了配合下一个属性
        webSettings.setLoadWithOverviewMode(true);//设置网页自适应屏幕大小，该属性必须和上一属性配合使用

//        webSettings.setGeolocationEnabled(true); //启用还H5的地理定位服务
//         设置是否允许webview使用缩放的功能
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮

//         提高网页渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        wvCommon.addJavascriptInterface(new WebAppInterface(getApplicationContext()), "Android");
        wvCommon.setWebViewClient(new WebViewClient());
        wvCommon.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pbLoad.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                tvTitle.setText(wvCommon.getTitle());
                pbLoad.setVisibility(View.GONE);
            }
        });
        wvCommon.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                pbLoad.setProgress(progress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        wvCommon.loadUrl(TEST_URL);
    }

    static class WebAppInterface {

        private Context mContext;

        public WebAppInterface(Context context) {
            mContext = context;
        }

        ;

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext.getApplicationContext(), toast, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        //检查是否有可回退的历史记录，有则回退到上一个浏览的网页，没有则执行程序本身的回退
        if (wvCommon.canGoBack()) {
            wvCommon.goBack();
            return;
        }
        super.onBackPressed();
    }

    public void backOnClick(View v) {
        finish();
    }

    public void moreOnClick(View v) {
        openWithBrowser();
    }

    public void backwardOnClick(View v) {
        if (wvCommon.canGoBack())
            wvCommon.goBack();
    }

    public void forwardOnClick(View v) {
        if (wvCommon.canGoForward())
            wvCommon.goForward();
    }

    public void refreshOnClick(View v) {
        wvCommon.reload();
    }

    private void openWithBrowser() {
        Uri uri = Uri.parse(TEST_URL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}