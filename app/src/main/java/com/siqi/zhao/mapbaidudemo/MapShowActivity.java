package com.siqi.zhao.mapbaidudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

/**
 * 地图展示
 */
public class MapShowActivity extends AppCompatActivity {

    private MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map_show);

        // 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.id_bmapView);
        BaiduMap map = mMapView.getMap();

        // 普通地图
        map.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //卫星地图
        // map.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        //开启交通图
        map.setTrafficEnabled(true);

        //开启热力图
        // map.setBaiduHeatMapEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mMapView = null;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
