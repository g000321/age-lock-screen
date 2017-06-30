package com.gym.agelockscan.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gym.R;
import com.gym.agelockscan.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 仿苹果风格dialog
 */
public class BaseDialog extends Dialog implements View.OnClickListener {

    private TextView titleTextView;

    private TextView contentTextView;

    private String title;

    private String content;

    private List<Event> events;

    private LinearLayout linearLayout;
    Context context;
    public BaseDialog(Context context) {
        super(context, R.style.BaseDialogStyle);
        this.context=context;
        this.setCancelable(false);
        events = new ArrayList<Event>();
        title = "";
//                getContext().getString(R.string.hint);

    }

    @Override
    public void show() {
        WindowManager.LayoutParams lp=getWindow().getAttributes();
        lp.width = LinearLayout.LayoutParams.WRAP_CONTENT;//宽高可设置具体大小
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        this.getWindow().setAttributes(lp);
        super.show();
    }

    boolean hasTitle=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_dialog_layout);

        titleTextView = (TextView) findViewById(R.id.dialog_title_textView);//����
        contentTextView = (TextView) findViewById(R.id.dialog_content_textView);//����
        if (hasTitle){
            titleTextView.setVisibility(View.VISIBLE);
            titleTextView.setText(title);
        }else
        {
            titleTextView.setVisibility(View.GONE);
        }


        contentTextView.setText(content);

        linearLayout = (LinearLayout) findViewById(R.id.dialog_linearLayout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        for (int i = 0; i < events.size(); i++) {
            if (i != 0) {
                View view = new View(context);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(1, ViewGroup.LayoutParams.MATCH_PARENT);
                params.setMargins(0, MathUtils.dip2px(context,3), 0, MathUtils.dip2px(context,3));
                view.setLayoutParams(params);
                view.setBackgroundColor(Color.parseColor("#b5b5b5"));
                linearLayout.addView(view);
            }
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams);
            Event event = events.get(i);
            textView.setText(event.name);
            textView.setOnClickListener(event.onClickListener);
            textView.setPadding(0
                    , MathUtils.dip2px(context,7)
                    , 0
                    , MathUtils.dip2px(context,7));
            textView.setTextColor(context.getResources().getColor(R.color.main_theme));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            linearLayout.addView(textView);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    /**
     * ��Ӱ���
     *
     * @param name            ���
     * @param onClickListener ����¼�
     */
    public BaseDialog addButton(String name, View.OnClickListener onClickListener) {
        Event event = new Event();
        event.name = name;
        event.onClickListener = onClickListener;
        events.add(event);
        return this;
    }

    /**
     * ���ȷ��
     */
    public BaseDialog addOk() {
        addOk(this);
        return this;
    }

    /**
     *
     */
    public BaseDialog addOk(View.OnClickListener onClickListener) {
        Event event = new Event();
        event.name ="确定";
        event.onClickListener = onClickListener;
        events.add(event);
        return this;
    }

    /**
     * ���ȡ���
     */
    public BaseDialog addCancel() {
        Event event = new Event();
        event.name = "取消";
        event.onClickListener = this;
        events.add(event);
        return this;
    }
    public BaseDialog addCancel(String  name) {
        Event event = new Event();
        event.name = name;
        event.onClickListener = this;
        events.add(event);
        return this;
    }
    /**
     * ���ñ���
     *
     * @param title ����
     */
    public BaseDialog setTitle(String title) {
        hasTitle=true;
        this.title = title;
        return this;
    }

    /**
     * ��������
     *
     * @param content ����
     */
    public BaseDialog setContent(String content) {
        this.content = content;
        return this;
    }


    /**
     * �¼������
     */
    class Event {

        public String name;

        public View.OnClickListener onClickListener;
    }
}
