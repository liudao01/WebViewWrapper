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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CommonWebViewActivity extends Activity {

    public static final String TEST_URL = "http://blog.csdn.net/liudao7994?skin=dark1";
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
        wvCommon.getSettings().setJavaScriptEnabled(true);
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