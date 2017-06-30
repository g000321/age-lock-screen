package com.gym.agelockscan.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gym.R;
import com.gym.agelockscan.dialog.BaseLoadingDialog;
import com.gym.agelockscan.utils.DialogUtils;
import com.gym.agelockscan.utils.ToastUtils;


public abstract class BaseActivity extends FragmentActivity {
    private TextView tvTitle;
    private boolean toastAutoCancel = true;

    /**
     * 当activity pause时候  toast是否自动取消
     * @param toastAutoCancel
     */
    public void setToastAutoCancel(boolean toastAutoCancel) {
        this.toastAutoCancel = toastAutoCancel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(retutnLayoutResID());
        tvTitle = (TextView) findViewById(R.id.head_title);
        if (tvTitle != null) {
            tvTitle.setText(setTitleString());

        }
    }

    /**
     * return一个布局文件 用来设置当前的activity
     *
     * @return
     */
    public abstract int retutnLayoutResID();

    /**
     * 设置一个标题
     *
     * @return
     */
    public abstract String setTitleString();

    /**
     * 获取标题String
     */
    public String getTitleString() {
        return tvTitle.getText().toString();
    }

    /**
     * activity销毁方法
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }


    public void errorDialog(String msg) {
        DialogUtils.setErrorDialog(this, msg);
    }

    public void errirToast(String msg) {
        ToastUtils.setErrorToast(this, msg);
    }

    public void okToast(String msg) {
        ToastUtils.setOKToast(this, msg);
    }

    public void setRightButton(int Resid) {
        ImageView rightButton = (ImageView) findViewById(R.id.head_right_button);
        rightButton.setImageResource(Resid);
        rightButton.setVisibility(View.VISIBLE);
    }

    public void setRightButtonText(String s) {
        Button rightButton = (Button) findViewById(R.id.head_right_text_button);
        rightButton.setText(s);
        rightButton.setVisibility(View.VISIBLE);
    }

    private BaseLoadingDialog progressDialog;
    public BaseLoadingDialog showProgressDialog(){
        return showProgressDialog("");
    }
    public BaseLoadingDialog showProgressDialog(String content) {
        if (progressDialog == null) {
            progressDialog = new BaseLoadingDialog.Builder(this).setContent(content).create();
        } else {
            progressDialog.setContent(content);
        }
        progressDialog.setCancelable(true);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 哭脸吐司
     *
     * @param context 上下�?
     * @param content 内容
     */
    public static void setErrorToast(Context context, String content) {
        ToastUtils.SetToast(context, R.drawable.base_toast_face_sad, content, 1000);
    }

    /**
     * 笑脸吐司
     *
     * @param context 上下�?
     * @param content 文字
     */
    public static void setOKToast(Context context, String content) {
        ToastUtils.SetToast(context, R.drawable.base_toast_face_ok, content, 1000);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (toastAutoCancel)
            ToastUtils.cancelToast();
    }
    public void setTitleLineColor(int color){
        View view=findViewById(R.id.view_title_line);
        view.setBackgroundColor(color);
    }
    public void finish(View v) {

    }
    public boolean isEmp(CharSequence charSequence){
        return TextUtils.isEmpty(charSequence);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }
}
