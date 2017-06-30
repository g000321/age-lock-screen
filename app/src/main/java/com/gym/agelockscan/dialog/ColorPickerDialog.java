package com.gym.agelockscan.dialog;

/**
 * 创建:gym
 * 日期:2016-12-22.
 * 说明:
 * 备注:
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.gym.R;
import com.gym.agelockscan.customviews.ColorPickerView;

public class ColorPickerDialog extends Dialog {
    ColorPickerView.OnColorChangedListener mListener;
    private ColorPickerView cpv;

            Context context;
            private String title;//标题



            /**
      *
      * @param context
      * @param listener 回调
      */
            public ColorPickerDialog(Context context,
                     ColorPickerView.OnColorChangedListener listener) {
                super(context);
                this.context = context;
                mListener = listener;

            }

            @Override  
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                requestWindowFeature(Window.FEATURE_NO_TITLE);
                setContentView(R.layout.dialog_color_select);
                cpv= (ColorPickerView) findViewById(R.id.cpv);
                cpv.setmListener(mListener);

            }
        }  