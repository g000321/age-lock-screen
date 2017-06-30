package com.gym.agelockscan.utils;

import android.content.Context;
import android.view.View;

import com.gym.agelockscan.dialog.BaseDialog;


public class DialogUtils {
    /**
     * 单个确定按钮dialog
     * @param context
     * @param msg
     */
    public static void setErrorDialog(Context context,String msg){
        final BaseDialog myDialog = new BaseDialog(context);
        myDialog.setContent(msg).addButton("确定", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        }).show();
    }
}
