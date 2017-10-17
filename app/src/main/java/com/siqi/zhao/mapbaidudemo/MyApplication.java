package com.siqi.zhao.mapbaidudemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * 初始化操作一般放在这里
 * Created by Bill on 2017/10/11.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化
        SDKInitializer.initialize(this);
    }
}
