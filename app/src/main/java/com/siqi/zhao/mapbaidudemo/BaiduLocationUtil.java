package com.siqi.zhao.mapbaidudemo;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 百度定位封装
 */

public class BaiduLocationUtil {

    private LocationClient locationClient;
    private static BaiduLocationUtil locationUtil;

    public static BaiduLocationUtil getInstance() {
        if (locationUtil == null) {
            synchronized (BaiduLocationUtil.class) {
                if (locationUtil == null) {
                    locationUtil = new BaiduLocationUtil();
                }
            }
        }
        return locationUtil;
    }

    public void getLocation(Context context, BDLocationListener listener) {

        locationClient = new LocationClient(context);
        // 设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 是否打开GPS
        option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
        option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
        option.setScanSpan(5000); // 设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);// 设置是否需要地址信息，默认不需要
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(listener);

        locationClient.start();

    }

    public void Unregister(BDLocationListener listener) {
        locationClient.stop();
        locationClient.unRegisterLocationListener(listener);
    }
}