package com.siqi.zhao.mapbaidudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_show)
    TextView mTvShow;
    @BindView(R.id.tv_location)
    TextView mTvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTvLocation.setOnClickListener(this);
        mTvShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            // 地图展示
            case R.id.tv_show:
                Intent intent = new Intent(this, MapShowActivity.class);
                startActivity(intent);
                break;

            // 地图定位
            case R.id.tv_location:
                Intent intent2 = new Intent(this, MapLocateActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
