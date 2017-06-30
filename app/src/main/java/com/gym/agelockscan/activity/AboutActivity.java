package com.gym.agelockscan.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gym.R;
import com.gym.agelockscan.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_developer)
    TextView tvDeveloper;
    @BindView(R.id.tv_explain)
    TextView tvExplain;

    @Override
    public int retutnLayoutResID() {
        return R.layout.activity_about;
    }

    @Override
    public String setTitleString() {
        return "关于";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        tvExplain.setText("说明:《"+getResources().getString(R.string.app_name)+"》是一款根据生日计算年龄并设置为锁屏的APP,软件仅供技术交流,请勿用于商业及非法用途,如产生法律纠纷与本人无关,建议反馈邮箱 623401935@qq.com");
        tvDeveloper.setText("开发者:" + "郭以明");
        tvVersion.setText("版本号:" + CommonUtils.getVersion(this));

    }
}
