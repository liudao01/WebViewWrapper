package com.madchan.webviewdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.common.lib_model_web.fragment.ProgressWebFragment;

public class TestWebActivity extends AppCompatActivity {


    private String title;
    private String url;


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
//        title = getIntent().getStringExtra(WebConstants.INTENT_TAG_TITLE);
        title = "腾讯网";
        url = "https://xw.qq.com/?f=qqcom";
//        url = getIntent().getStringExtra(WebConstants.INTENT_TAG_URL);
        setTitle(title);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        ProgressWebFragment webviewFragment = ProgressWebFragment.newInstance(url);
        transaction.replace(R.id.web_view_fragment, webviewFragment).commit();
    }
}