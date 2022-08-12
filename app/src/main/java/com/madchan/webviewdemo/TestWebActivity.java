package com.madchan.webviewdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.widget.Button;

import com.common.lib_model_web.brige.JavaScriptInterfaceApi;
import com.common.lib_model_web.fragment.ProgressWebFragment;

import io.sentry.Sentry;

public class TestWebActivity extends AppCompatActivity {


    private String title;
    private String url;
    private Button btCallH5;

    private Button btCall;


//    public static void start(Context context, String title, String url, int testLevel) {
//        Intent intent = new Intent(context, WebActivity.class);
//        intent.putExtra(WebConstants.INTENT_TAG_TITLE, title);
//        intent.putExtra(WebConstants.INTENT_TAG_URL, url);
//        intent.putExtra("level", testLevel);
//        if (context instanceof Service) {
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_web);
        btCallH5 = findViewById(R.id.bt_call_h5);
        btCall = findViewById(R.id.bt_call);

//        title = getIntent().getStringExtra(WebConstants.INTENT_TAG_TITLE);
        String title = "腾讯网";

//        String url = "https://xw.qq.com/?f=qqcom";
        url = "file:///android_asset/index.html";
//        url = getIntent().getStringExtra(WebConstants.INTENT_TAG_URL);
        setTitle(title);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

//        ProgressWebFragment webviewFragment = ProgressWebFragment.newInstance(url);

        ProgressWebFragment webviewFragment = ProgressWebFragment.newInstance(url, NativeMethods.class);
        transaction.replace(R.id.web_view_fragment, webviewFragment).commit();

//        webviewFragment.getWebView().evaluateJavascript("",new ValueCallback(){
//
//            @Override
//            public void onReceiveValue(Object o) {
//
//            }
//        });

        btCallH5.setOnClickListener(view -> {
            webviewFragment.getApi().executeJavascript("androidToJs(333)", new JavaScriptInterfaceApi.JavascriptCallback() {
                @Override
                public void onReceiveValue(String value) {
                    Log.d("jsMethods", "onReceiveValue: " + value);
                }
            });

        });
        btCall.setOnClickListener(view -> {
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                Sentry.captureException(e);
            }
            webviewFragment.getApi().executeJavascript("callJS()", new JavaScriptInterfaceApi.JavascriptCallback() {
                @Override
                public void onReceiveValue(String value) {
                    Log.d("jsMethods", "onReceiveValue: " + value);

                }
            });

        });
        Sentry.captureMessage("testing SDK setup");

    }
}