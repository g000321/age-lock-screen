package com.gym.agelockscan.utils;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.gym.agelockscan.activity.WebActivity;


//import com.gym.zdj.activity.ForgetPassword;

public class CommonUtils {
    /**
     * 无参数打开一个activi
     * 
     * @author guoyi
     * @title 修改跳转页的标题
     */
    public static <T> void startActivity(Context context, Class<T> clazz, String... title) {
        Intent intent = new Intent(context, clazz);
        if (title != null) {
            intent.putExtra("titleString", title);
        }
        context.startActivity(intent);
    }
    /**
     * 清空通知栏
     * @param context
     */
    public void clearnAllNotify(Context context) {
        /** Notification管理 */
        NotificationManager mNotificationManager;
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }

    /**
     * 解决ScrollView嵌套ListView只显示一行的问题
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    /**
     * 启动内部浏览器
     * @param context
     * @param title
     * @param url
     */
    public static void startWebActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}