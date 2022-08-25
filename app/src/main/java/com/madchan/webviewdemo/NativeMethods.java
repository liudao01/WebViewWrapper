package com.madchan.webviewdemo;

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


public class NativeMethods implements Serializable {
    @JavascriptInterface
    public String testSyn(Object msg)  {
        return msg + "［syn call］";
    }

    @JavascriptInterface
    public void testAsyn(Object msg, CompletionHandler<String> handler){
        handler.complete(msg+" [ asyn call]");
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
     * @param callBack
     */
    @JavascriptInterface
    public static void getSetingData( Object msg, CompletionHandler<String> callBack) {
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
     * @param callBack
     */
    @JavascriptInterface
    public static void exitSystem( Object msg, CompletionHandler<String> callBack) {
        LogUtil.d("退出系统");
//        AppManager.getAppManager().finishAllActivity();
        System.exit(0);
        if (callBack != null) {
        }
    }



    @JavascriptInterface
    public static void showToast( JSONObject arg,  CompletionHandler<String> callBack) {
        String message = arg.optString("msg");

//        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

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
     * 最小化
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void minimize(JSONObject arg,  CompletionHandler<String> callBack) {
        String message = arg.optString("msg");

//        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setAction(Intent.ACTION_MAIN);
//        AppManager.getAppManager().currentActivity().startActivity(intent);
//        AppManager.getAppManager().currentActivity().moveTaskToBack(true);
        if (callBack != null) {
            try {
                JSONObject result = new JSONObject();
//                result.put("msg", "NativeMethods js 调用 native 成功！");
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
     * @param arg
     * @param callBack
     */


    /**
     * 退出系统
     *
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void exitSystem(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
        LogUtil.d("退出系统");
//        AppManager.getAppManager().finishAllActivity();
        System.exit(0);
        if (callBack != null) {
        }
    }

    /**
     * 打开钱箱
     *
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void openCashBox(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
        LogUtil.d("打开钱箱");
//        PrinterCallBack printerCallBack = new PrinterCallBack() {
//            @Override
//            public void onPrintError(String erMsg) {
//                LogUtil.i("打印错：" + erMsg);
//                ToastUtils.showCenter("打印错误: " + erMsg);
//            }
//
//            @Override
//            public void onPrintFinish() {
////                LogUtil.i("打成功");
//                ToastUtils.showCenter("打印成功");
//            }
//
//            @Override
//            public void onPrinterOutOfPaper() {
//                ToastUtils.showCenter("打印机缺纸");
//            }
//        };
//
//        /**
//         * 打开钱箱
//         *
//         * @param context
//         */
//        // 钱箱和打印机用的是一个服务
//        DeviceHelperProxy.getInstance().getDeviceManager()
//                .getPrinter(printerCallBack)
//                .setContext(MyApplication.getInstance())
//                .openDrawer();

    }

    /**
     * 打开设置页面
     *
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void openSeting(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
        LogUtil.d("打开设置页面");
//        spManager.setLogin(false);
//        Intent intent = new Intent(AppManager.getAppManager().currentActivity(), SetingActivity.class);
////        Intent intent = new Intent(AppManager.getAppManager().currentActivity(), SetingActivity.class);
////        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        MyApplication.getAppInstance().startActivity(intent);
//        MainActivity.mainActivity.startActivity(intent);
//        AppManager.getAppManager().finishActivity(MainActivity.class);
//        if (xTcpClient != null) {
//            xTcpClient.disconnect();//activity销毁时断开tcp连接
//        }
    }

    //正确的储值卡返回值
    //000000C61B4200C2010230303030303135B8F6C8CBBFA820202020202020202020202020202020202020202020200133000000000000000038383838313138303031303030353930303135000000000000000000000000000000000000000000000000000000000000000030303030303030303030303030303030303030303030303030302020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020000000303030303030301B4521
    @JavascriptInterface
    public static void sendMessage(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
//        com.pos.manager.util.StringUtils.byte2hex();

//        arg 里面获取储值卡刷卡
//        if (xTcpClient != null) {
//            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
////            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
////            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
//        } else {
//            ToastUtils.showCenter("还没有连接到服务器");
//        }
    }

    static CompletionHandler commandCallBack = null;

    public static CompletionHandler getCommandCallback() {

        return commandCallBack;
    }


    /**
     * 执行链接EDC命令
     *
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void executeCommand(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
//        com.pos.manager.util.StringUtils.byte2hex();

//        arg 里面获取储值卡刷卡
//        if (xTcpClient != null) {
//            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
////            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
////            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
//        } else {
//            ToastUtils.showCenter("还没有连接到服务器");
//        }
//        if (callBack != null) {
//            commandCallBack = callBack;
////            try {
////                JSONObject result = new JSONObject();
////                result.put("msg", "NativeMethods js 调用 native 成功！");
////                callBack.apply(result);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        }
    }

    /**
     * 刷储值卡
     *
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void executeVipCardCommand(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
//        com.pos.manager.util.StringUtils.byte2hex();
//        LogUtil.d("executeVipCardCommand 里面获取储值卡刷卡   =  " + GsonUtil.gsonString(arg));
////        arg 里面获取储值卡刷卡
//        if (xTcpClient != null) {
//            type = 1;
//            LogUtil.d("进行刷卡1B42000501011B455F");
//            boolean connected = xTcpClient.isConnected();
//            LogUtil.d("是否连接 " + connected);
//
//            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
////            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
////            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
//        } else {
//            ToastUtils.showCenter("还没有连接到服务器");
//            return;
//        }
//        Thread thread = Thread.currentThread();
//        LogUtil.d("thread = " + thread.getName() + "  thread = " + thread.getId());
//        if (callBack != null) {
//            commandCallBack = callBack;
////            try {
////                JSONObject result = new JSONObject();
////                result.put("msg", "");
////                callBack.apply(result);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        }
    }

    /**
     * 刷银行卡
     *
     * @param view
     * @param arg
     * @param callBack
     */
    @JavascriptInterface
    public static void executeBankCandCommand(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
//        com.pos.manager.util.StringUtils.byte2hex();
//        arg 里面获取储值卡刷卡
//        if (xTcpClient != null) {
//            type = 2;
//            LogUtil.d("arg.toString()里面获取银行卡刷卡 = "+arg.toString());
//            //将Json数据转化为报文
//            String s1 = EdcConnectUtil.sendBankCard(arg.toString());
//            String s = com.pos.manager.util.StringUtils.string2Hex(arg.toString());
//            LogUtil.d("刷银行卡命令 == "+s);
//
////            xTcpClient.sendMsg(s1);//储值卡   错误返回 0000000B1B420007010230371B455B
////            xTcpClient.sendMsg("1B42000501011B455F");//储值卡   错误返回 0000000B1B420007010230371B455B
//        } else {
//            ToastUtils.showCenter("还没有连接到服务器");
//            return;
//        }
//        Thread thread = Thread.currentThread();
//        LogUtil.d("thread = " + thread.getName() + "  thread = " + thread.getId());
//        if (callBack != null) {
//            commandCallBack = callBack;
////            try {
////                JSONObject result = new JSONObject();
////                result.put("msg", "NativeMethods js 调用 native 成功！");
////                callBack.apply(result);
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        }
    }


    /**
     * 打印方法
     *
     * @param view
     * @param arg
     * @param callBack
     */
//    @JavascriptInterface
//    public static void print(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
//        LogUtil.d("print arg = " + arg.toString());
////        String path = null;
////        try {
////            path = PrintImageManager.getInstance().createImageView(arg.toString());
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        PrinterCallBack printerCallBack = new PrinterCallBack() {
//            @Override
//            public void onPrintError(String erMsg) {
//                LogUtil.i("打印错：" + erMsg);
//                ToastUtils.showCenter("打印错误: " + erMsg);
//            }
//
//            @Override
//            public void onPrintFinish() {
////                LogUtil.i("打成功");
//                ToastUtils.showCenter("打印成功");
//            }
//
//            @Override
//            public void onPrinterOutOfPaper() {
//                ToastUtils.showCenter("打印机缺纸");
//            }
//        };
//
//        try {
//            DeviceHelperProxy.getInstance().getDeviceManager()
//                    .getPrinter(printerCallBack)
//                    .setContext(MyApplication.getInstance().getApplicationContext())
//                    .printText(arg.toString(), printerCallBack);
//
//            if (callBack != null) {
//                commandCallBack = callBack;
//                try {
//                    JSONObject result = new JSONObject();
////                result.put("msg", "NativeMethods js 调用 native 成功！");
//                    callBack.complete(result.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
////            printListener.printError("打印失败");
//            LogUtil.e("e = " + e.getMessage());
//            e.printStackTrace();
//
//        }
//    }

    /**
     * 更新APP
     *
     * @param view
     * @param arg
     * @param callBack
     */
//    @JavascriptInterface
//    public static void updateApp(WebView view, JSONObject arg,  CompletionHandler<String> callBack) {
//        String downloadUrl = "";
//        try {
//            downloadUrl = (String) arg.get("downloadUrl");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if (BuildConfig.DEBUG) {
//            downloadUrl = "http://10.26.2.109:8080/download/POS_ANDROID/app-debug.apk";
//        }
//        //
////        String url = "https://test-oss.sinochemoilmarketing.com/appDownload/中化油达人release.apk";
//        boolean orExistsDir = FileUtils.createOrExistsDir(ApkFilePath);
//        if (StringUtils.isEmpty(downloadUrl)) {
//            ToastUtils.showCenter("下载地址为空");
//            return;
//        }
//        String name = "download.apk";
//        downLoadApk(downloadUrl, ApkFilePath, name, MyApplication.getInstance().getApplicationContext(), new IUpdateHttpService.DownloadCallback() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onProgress(float progress, long total) {
//
//            }
//
//            @Override
//            public void onSuccess(File file) {
//                // renameFile(oldFileName, fileName);
////                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                intent.setDataAndType(Uri.fromFile(new File(ApkFilePath + File.separator + name)),
////                        "application/vnd.android.package-archive");
////                MyApplication.getAppInstance().startActivity(intent);
////                IntentUtils.getInstallAppIntent(file,AppUtils.installApp();)
////                ThreadUtils.runUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////
////
////                    }
////                });
//                AppUtils.installApp(file, AppUtils.getAppPackageName() + ".fileprovider");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//        });
//    }

//    @JavascriptInterface
//    public static void testDownload() {
//        String url = "https://test-oss.sinochemoilmarketing.com/appDownload/中化油达人release.apk";
//        boolean orExistsDir = FileUtils.createOrExistsDir(ApkFilePath);
//        String name = "posmanager.apk";
//        downLoadApk(url, ApkFilePath, name, MyApplication.getInstance().getApplicationContext(), new IUpdateHttpService.DownloadCallback() {
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onProgress(float progress, long total) {
//
//            }
//
//            @Override
//            public void onSuccess(File file) {
//                // renameFile(oldFileName, fileName);
////                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                intent.setDataAndType(Uri.fromFile(new File(ApkFilePath + File.separator + name)),
////                        "application/vnd.android.package-archive");
////                MyApplication.getAppInstance().startActivity(intent);
//                String string = AppUtils.getPackageName(MyApplication.getInstance()) + ".fileprovider";
//                Intent installAppIntent = IntentUtils.getInstallAppIntent(file, string);
//                MyApplication.getAppInstance().startActivity(installAppIntent);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//        });
//    }

    static float currentProgress = 0;

//    private static void downLoadApk(String url, String path, String fileName, Context context, final IUpdateHttpService.DownloadCallback callback) {
//        android.androidlib.utils.LogUtil.d("path = " + path);
//        android.androidlib.utils.LogUtil.d("fileName = " + fileName);
//        android.androidlib.utils.LogUtil.d("下载url = " + url);
//        callback.onStart();
//        if (FileUtils.isFileExists(path + fileName)) {
//            FileUtils.deleteFile(path + fileName);
//        }
//
//        ThreadUtils.runUiThread(new Runnable() {
//            @Override
//            public void run() {
//                DialogUtils.showWaitingDialog("新版本下载中", AppManager.getAppManager().currentActivity(), false);
//                DialogUtils.showProgress("0");
//            }
//        });
//
//
//        XHttp.getInstance().download(context, url, path, fileName, new HttpCallBack() {
//            @Override
//            public void onSuccess(Object o) {
//                android.androidlib.utils.LogUtil.d("下载结束" + o.toString());
//                callback.onSuccess(new File(o.toString()));
//                currentProgress = 0;
//                ThreadUtils.runUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        DialogUtils.hideWaitingDialog();
//                    }
//                });
//            }
//
//            @Override
//            public void onProgress(long bytesDownloaded, long totalBytes) {
//                super.onProgress(bytesDownloaded, totalBytes);
//                double divide = MathExtendUtils.divide(bytesDownloaded, totalBytes);
////                double v = (double) bytesDownloaded / (double) totalBytes;
////                BigDecimal b = new BigDecimal(v);
////                double f1  b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
//                float progress = (float) divide;
////                LogUtil.d("divide = = " + divide);
//                if (currentProgress < progress) {
//                    currentProgress = progress;
//                    double round = MathExtendUtils.round(currentProgress * 100, 0);
//                    ThreadUtils.runUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            DialogUtils.showProgress(String.valueOf(round));
//                        }
//                    });
//                    callback.onProgress(Float.valueOf(String.valueOf(divide)), totalBytes);
//                    android.androidlib.utils.LogUtil.d("onProgress currentProgress = " + currentProgress);
//                    android.androidlib.utils.LogUtil.d("bytesDownloaded = " + bytesDownloaded + " totalBytes = " + totalBytes);
//                }
//            }
//
//            @Override
//            public void onFailed(int errorCode, String error) {
//                super.onFailed(errorCode, error);
//                ToastUtils.showCenter("下载失败");
//                callback.onError(new Exception(error));
//                ThreadUtils.runUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        DialogUtils.hideWaitingDialog();
//                    }
//                });
//
//            }
//
//
//        }, false);
//
//
//    }


}
