package com.madchan.webviewdemo;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.common.lib_model_web.uti.LogUtil;

import org.json.JSONObject;

import java.io.Serializable;
import wendu.dsbridge.CompletionHandler;


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

}
