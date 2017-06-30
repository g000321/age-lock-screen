package com.gym.agelockscan.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.gym.R;
import com.gym.agelockscan.utils.ConfigSharedPreferences;
import com.gym.agelockscan.utils.Logger;
import com.gym.agelockscan.utils.MathUtils;
import com.gym.agelockscan.utils.TimeUtils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class AgeLockActivity extends Activity implements View.OnClickListener {

    private static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.bt_open)
    Button btOpen;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private boolean isRun = true;
    String threadTag="";

    String birthDay = "";
    long birthDayLine;
    int bgColor;
    int textColor;
    Thread timeThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_lock);
        ButterKnife.bind(this);
        getSPData();
        btOpen.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        btOpen.setOnClickListener(this);
        btOpen.requestFocus();
    }

    private void startTimeThread() {
        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRun) {
                    Logger.e("aaa", "运行");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                            long ctm=System.currentTimeMillis();
                            Date curDate = new Date();//获取当前时间
                            String str = formatter.format(curDate);
                            tvTime.setText(str);
                            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy年MM月dd日");
                            String str2 = formatter2.format(curDate);
                            tvData.setText(str2);
                            String aa = MathUtils.get4down5up2d(((double) ctm - (birthDayLine)) / 1000 / 60 / 60 / 24 / 365.25) + "";
                            tvAge.setText(aa);
                        }
                    });

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    }
                }
            }
        });
        timeThread.start();

    }

    @Override
    public void onAttachedToWindow() {
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);//关键代码
        super.onAttachedToWindow();
    }

    @SuppressWarnings("static-access")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {
            return true;
        }
        if (keyCode == event.KEYCODE_HOME) {
            return true;
        }
        if (keyCode == event.KEYCODE_APP_SWITCH) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {//MENU键
            //监控/拦截菜单键
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSPData();
        isRun = true;
        startTimeThread();

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRun = false;
        timeThread.interrupt();
        }





    public void getSPData() {
        bgColor = Integer.parseInt(ConfigSharedPreferences.getInstance().getbgColor());
        birthDay = ConfigSharedPreferences.getInstance().getBirthDay().replace("年", "-").replace("月", "-").replace("日", "");
        birthDayLine=TimeUtils.date2TimeStamp(birthDay + " 00:00:00");
        textColor = Integer.parseInt(ConfigSharedPreferences.getInstance().gettextColor());
        tvTime.setTextColor(textColor);
        tvData.setTextColor(textColor);
        tvAge.setTextColor(textColor);
        btOpen.setTextColor(textColor);
        rlRoot.setBackgroundColor(bgColor);
        Window window=getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(bgColor);   //这里动态修改颜色
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
