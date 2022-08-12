package com.madchan.webviewdemo;

import android.app.Application;

import io.sentry.SentryLevel;
import io.sentry.android.core.SentryAndroid;

/**
 * Created by liuml on 2022/8/12 10:47
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        SentryAndroid.init(this, options -> {
//            options.setDsn("http://36979cde696e4aff92bd11966f552f19@10.26.5.244:9000/4");
//            // Add a callback that will be used before the event is sent to Sentry.
//            // With this callback, you can modify the event or, when returning null, also discard the event.
//            options.setBeforeSend((event, hint) -> {
//                if (SentryLevel.DEBUG.equals(event.getLevel()))
//                    return null;
//                else
//                    return event;
//            });
//        });
    }
}