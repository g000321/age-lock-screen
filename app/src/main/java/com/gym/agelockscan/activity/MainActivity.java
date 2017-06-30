package com.gym.agelockscan.activity;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;

import com.gym.R;
import com.gym.agelockscan.customviews.ColorPickerView;
import com.gym.agelockscan.dialog.ColorPickerDialog;
import com.gym.agelockscan.service.LockService;
import com.gym.agelockscan.utils.CommonUtils;
import com.gym.agelockscan.utils.ConfigSharedPreferences;
import com.gym.agelockscan.utils.DateTimePickDialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建:gym
 * 日期:2016-12-16.
 * 说明:
 * 备注:
 */

public class MainActivity extends Activity implements DateTimePickDialogUtil.DataSetListener {

    @BindView(R.id.bt_time)
    Button btTime;
    @BindView(R.id.bt_color)
    Button btColor;
    @BindView(R.id.bt_bg_color)
    Button btBgColor;
    @BindView(R.id.bt_scan)
    Button btScan;
    @BindView(R.id.bt_about)
    Button btAbout;
    @BindView(R.id.sw_all)
    SwitchCompat swAll;
    private String initDateTime = "1994年04月04日"; // 初始化时间
    DateTimePickDialogUtil dateTimePicKDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //开始服务
        service= new Intent();
        service.setClass(this, LockService.class);
        inspactData();
        initScanLock();
        initView();
        upViewState();
        upServerState();

    }
    Intent service;
    private void upServerState() {
        if (ConfigSharedPreferences.getInstance().getSwAll()){

            startService(service);


//            //注册广播
//            receiveBroadCast = new MyBroadcast();
//            IntentFilter filter = new IntentFilter();
//            filter.addAction("action"); // 只有持有相同的action的接受者才能接收此广播
//            registerReceiver(receiveBroadCast, filter);
//
//            //发送广播
//            Intent intent = new Intent();
//            intent.setAction("action");
//            this.sendBroadcast(intent);

        }else
        {
//            if (receiveBroadCast!=null)
//            unregisterReceiver(receiveBroadCast);
            stopService(service);
        }
    }

    private void upViewState() {
        btBgColor.setTextColor(Integer.parseInt(ConfigSharedPreferences.getInstance().getbgColor()));
        btColor.setTextColor(Integer.parseInt(ConfigSharedPreferences.getInstance().gettextColor()));
        swAll.setChecked(ConfigSharedPreferences.getInstance().getSwAll());
        
        
    }

    private void inspactData() {
        if (ConfigSharedPreferences.getInstance().getBirthDay().isEmpty()) {
            ConfigSharedPreferences.getInstance().setBirthDay(initDateTime);
        }
        if (ConfigSharedPreferences.getInstance().gettextColor().isEmpty()) {
            ConfigSharedPreferences.getInstance().settextColor(Color.WHITE + "");
        }
        if (ConfigSharedPreferences.getInstance().getbgColor().isEmpty()) {
            ConfigSharedPreferences.getInstance().setbgColor(Color.BLACK + "");

        }
    }

    private void initView() {
        dateTimePicKDialog = new DateTimePickDialogUtil(
                this, ConfigSharedPreferences.getInstance().getBirthDay() + " 00:00");
        btTime.setText(ConfigSharedPreferences.getInstance().getBirthDay());
        dateTimePicKDialog.setDataSetListener(this);
        swAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ConfigSharedPreferences.getInstance().setSwAll(isChecked);
                upServerState();
            }
        });
    }

    /**
     * 初始化屏幕锁 广播
     */
    private void initScanLock() {

    }


    @Override
    public void onDataSet(String data) {
        ConfigSharedPreferences.getInstance().setBirthDay(data);
    }

    ColorPickerDialog colorPickerDialog;

    @OnClick({R.id.bt_time, R.id.bt_color, R.id.bt_bg_color, R.id.bt_scan, R.id.bt_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_time:
                dateTimePicKDialog.dateTimePicKDialog(btTime);
                break;
            case R.id.bt_color:
                colorPickerDialog = new ColorPickerDialog(this, new ColorPickerView.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        ConfigSharedPreferences.getInstance().settextColor(color + "");
                        upViewState();
                        colorPickerDialog.dismiss();
                    }
                });
                colorPickerDialog.show();
                break;
            case R.id.bt_bg_color:
                colorPickerDialog = new ColorPickerDialog(this, new ColorPickerView.OnColorChangedListener() {
                    @Override
                    public void colorChanged(int color) {
                        ConfigSharedPreferences.getInstance().setbgColor(color + "");
                        upViewState();
                        colorPickerDialog.dismiss();
                    }
                });
                colorPickerDialog.show();
                break;
            case R.id.bt_scan:
                Intent intent = new Intent(this, AgeLockActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.bt_about:
                CommonUtils.startActivity(this, AboutActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        unregisterReceiver(receiveBroadCast);
    }


}
