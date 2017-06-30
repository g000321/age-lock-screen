package com.gym.agelockscan.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.gym.R;
import com.gym.agelockscan.customviews.LoadingView;


public class BaseLoadingDialog extends Dialog{

    private Context context;

    public BaseLoadingDialog(Context context) {
        super(context);
        this.context = context;
    }

    protected BaseLoadingDialog(Context context, boolean cancelable,
                                OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    public BaseLoadingDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public BaseLoadingDialog setContent(String content) {
        TextView textView = (TextView) findViewById(R.id.dialog_progress_tv);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.dialog_progress_rl);
        LoadingView loadingView= (LoadingView) findViewById(R.id.loading);
        loadingView.startRotationAnimation();
        if (TextUtils.isEmpty(content)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(content);
            textView.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public static class Builder {
        private Context context;
        private String content;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public BaseLoadingDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final BaseLoadingDialog dialog = new BaseLoadingDialog(context, R.style.base_load_dialog);
            View layout = inflater.inflate(R.layout.base_progress, null);
            dialog.addContentView(layout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.getWindow().getAttributes().dimAmount = 0f;
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(true);
            RelativeLayout relativeLayout = (RelativeLayout) layout.findViewById(R.id.dialog_progress_rl);
            TextView textView = (TextView) layout.findViewById(R.id.dialog_progress_tv);
            if (TextUtils.isEmpty(content)) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(content);
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
