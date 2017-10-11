package com.siqi.zhao.mapbaidudemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Bill on 2017/10/11.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
