package com.common.lib_model_web.brige;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.FileUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.common.lib_model_web.MainActivity;
import com.common.lib_model_web.uti.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;

import wendu.dsbridge.CompletionHandler;

/**
 * Created by liuml on 2022/8/15 16:10
 */
public class NativeMethods implements Serializable {
    //for synchronous invocation
    @JavascriptInterface
    public String testSyn(Object msg) {
        return msg + "［syn call］";
    }

    //for asynchronous invocation
    @JavascriptInterface
    public void testAsyn(Object msg, CompletionHandler handler) {
        handler.complete(msg + " [ asyn call]");
    }


    public static int type = 1;//1 储值卡  2 银行卡

    @JavascriptInterface
    public static void showToast(WebView view, Object msg, CompletionHandler<String> callBack) {
        JSONObject arg = (JSONObject) msg;
        String message = arg.optString("msg");

        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        if (callBack != null) {
            try {
                JSONObject result = new JSONObject();
                result.put("msg", "NativeMethods js 调用 native 成功！");
                callBack.complete(result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 同步网络配置
     *
     * @param view
     * @param callBack
     */
    @JavascriptInterface
    public static void getSetingData(WebView view, Object msg, CompletionHandler<String> callBack) {
        LogUtil.d("同步网络配置");

//        String message = arg.optString("msg");
        if (callBack != null) {
            try {
                JSONObject result = new JSONObject();
                result.put("ebs", "10.26.2.109:8080");
                result.put("edc", "10.26.25.234:8888");
                result.put("deviceId", "pos_109_lml");
                result.put("deviceType", "POS_ANDROID");
//                result.put("deviceVersion", AppUtils.getVersionName(MyApplication.getAppInstance()));
                callBack.complete(result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 退出系统
     *
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void exitSystem(WebView view, Object msg, CompletionHandler<String> callBack) {
        LogUtil.d("退出系统");
//        AppManager.getAppManager().finishAllActivity();
        System.exit(0);
        if (callBack != null) {
        }
    }



    @JavascriptInterface
    public String testNoArgSyn(Object arg) throws JSONException {
        return  "testNoArgSyn called [ syn call]";
    }

    @JavascriptInterface
    public void testNoArgAsyn(Object arg,CompletionHandler<String> handler) {
        handler.complete( "testNoArgAsyn   called [ asyn call]");
    }


    //@JavascriptInterface
    //without @JavascriptInterface annotation can't be called
    public String testNever(Object arg) throws JSONException {
        JSONObject jsonObject= (JSONObject) arg;
        return jsonObject.getString("msg") + "[ never call]";
    }

    @JavascriptInterface
    public void callProgress(Object args, final CompletionHandler<Integer> handler) {

        new CountDownTimer(11000, 1000) {
            int i=10;
            @Override
            public void onTick(long millisUntilFinished) {
                //setProgressData can be called many times util complete be called.
                handler.setProgressData((i--));

            }
            @Override
            public void onFinish() {
                //complete the js invocation with data; handler will be invalid when complete is called
                handler.complete(0);

            }
        }.start();
    }
}