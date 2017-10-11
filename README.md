# MapBaidu

百度地图的展示（热力图、交通图、普通地图、卫星图等）和定位   简单集成和效果实现

集成步骤
    
    首先，去官网去注册,百度地图官网:http://lbsyun.baidu.com/
    拿到密钥:AppKey:http://lbsyun.baidu.com/apiconsole/key
    申请密钥的步骤具体在:http://lbsyun.baidu.com/index.php?title=android-locsdk/guide/key
    下载第三方Jar包和一些SO文件:http://lbsyun.baidu.com/index.php?title=android-locsdk/geosdk-android-download

环境配置
    
    详情见代码：


1.地图展示 
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


2.地图定位
    public class MapLocateActivity extends AppCompatActivity {
    
        @BindView(R.id.bmapView)
        MapView mMapView;
        @BindView(R.id.address)
        TextView mAddress;
    
        // 定位相关
        LocationClient mLocClient;
        //定位监听
        public MyLocationListener myListener = new MyLocationListener();
    
        BaiduMap mBaiduMap;
        boolean isFirstLoc = true; // 是否首次定位
        BDLocation mlocation;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
    
            setContentView(R.layout.activity_map_locate);
            ButterKnife.bind(this);
    
            mBaiduMap = mMapView.getMap();
    
            // 开启定位图层
            mBaiduMap.setMyLocationEnabled(true);
    
            // 定位初始化
            mLocClient = new LocationClient(this);
            mLocClient.registerLocationListener(myListener);
    
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            option.setAddrType("all");
            mLocClient.setLocOption(option);
            mLocClient.start();
    
        }
    
        /**
         * 定位SDK监听函数
         */
        public class MyLocationListener implements BDLocationListener {
    
            @Override
            public void onReceiveLocation(BDLocation location) {
                // map view 销毁后不在处理新接收的位置
                if (location == null || mMapView == null) {
                    return;
                }
    
                mlocation = location;
    
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(mlocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(100).latitude(mlocation.getLatitude())
                        .longitude(mlocation.getLongitude()).build();
    
                mBaiduMap.setMyLocationData(locData);
    
                if (isFirstLoc) {
                    isFirstLoc = false;
                    LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(ll).zoom(18.0f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                }
    
                // 地址设置
                mAddress.setText("维度:" + mlocation.getLatitude() + "\n"
                        + "精度:" + mlocation.getLongitude() + "\n"
                        + "地址:" + mlocation.getAddrStr());
            }
        }
    
        @Override
        protected void onPause() {
            //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
            mMapView.onPause();
            super.onPause();
        }
    
        @Override
        protected void onResume() {
            //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
            mMapView.onResume();
            super.onResume();
        }
    
        @Override
        protected void onDestroy() {
            // 退出时销毁定位
            mLocClient.stop();
    
            // 关闭定位图层
            mBaiduMap.setMyLocationEnabled(false);
            mMapView.onDestroy();
            mMapView = null;
            super.onDestroy();
        }
    }
