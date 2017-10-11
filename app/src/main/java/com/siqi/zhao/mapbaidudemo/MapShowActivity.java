package com.siqi.zhao.mapbaidudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地图展示
 */
public class MapShowActivity extends AppCompatActivity {

    @BindView(R.id.tv_common)
    TextView mTvCommon;
    @BindView(R.id.tv_star)
    TextView mTvStar;
    @BindView(R.id.tv_hot)
    TextView mTvHot;
    @BindView(R.id.tv_traffic)
    TextView mTvTraffic;
    @BindView(R.id.id_bmapView)
    MapView mIdBmapView;

    private MapView mMapView = null;
    private BaiduMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map_show);
        ButterKnife.bind(this);

        // 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.id_bmapView);
        mMap = mMapView.getMap();

       /* // 普通地图
        mMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);*/
    }

    @OnClick(R.id.tv_common)
    public void onMTvCommonClicked() {
        mMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        mMap.setBaiduHeatMapEnabled(false);
        mMap.setTrafficEnabled(false);

        // 普通地图
        mMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    @OnClick(R.id.tv_star)
    public void onMTvStarClicked() {
        mMap.setBaiduHeatMapEnabled(false);
        mMap.setTrafficEnabled(false);

        //卫星地图
        mMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
    }

    @OnClick(R.id.tv_hot)
    public void onMTvHotClicked() {
        mMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mMap.setTrafficEnabled(false);

        //开启热力图
        mMap.setBaiduHeatMapEnabled(true);
    }

    @OnClick(R.id.tv_traffic)
    public void onMTvTrafficClicked() {
        mMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mMap.setBaiduHeatMapEnabled(false);

        //开启交通图
        mMap.setTrafficEnabled(true);
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
