# MapBaidu
百度地图定位和展示  简单集成和效果实现


集成步骤
    和所有的第三方集成SDK一样,首先当然是去官网去注册,百度地图官网:http://lbsyun.baidu.com/
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
    
