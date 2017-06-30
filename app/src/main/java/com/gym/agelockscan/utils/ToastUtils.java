/**
 * 
 */
package com.gym.agelockscan.utils;


import android.content.Context;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gym.R;


public class ToastUtils {
    static Toast toast;


	public static void show(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void show(Context context, int info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
	
	    
    public static Toast  SetToast(Context context, int imageId, Spanned fromHtml, int duration) {
        return init(context,imageId,null,fromHtml,duration);

    }
    public static Toast SetToast(Context context , int imageId ,String content , int duration){
       return init(context,imageId,content,null,duration);
    }

    /**
     * 初始化并且显示
     */
    private static Toast init(Context context,int imageId,String content,Spanned spanned,int duration) {
        if (toast!=null)
            toast.cancel();
        View layout = LayoutInflater.from(context).inflate(R.layout.base_toast,
                null);
        ImageView image = (ImageView) layout
                .findViewById(R.id.tvImageToast);
        image.setBackgroundResource(imageId);
        TextView text = (TextView) layout.findViewById(R.id.tvTitleToast);
        if (spanned==null) {
            text.setText(content);
        }else{
            text.setText(spanned);
        }
        toast= new Toast(context.getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
        return toast;
    }

    /**
     * 哭脸吐司
     * @param content 内容
     */
    public static void setErrorToast(Context context,String content){
       SetToast(context ,R.drawable.base_toast_face_sad ,content , 1000);
    }
    /**
     * 笑脸吐司
     * @param content 文字
     */
    public static void setOKToast(Context context,String content){
        SetToast(context ,R.drawable.base_toast_face_ok ,content , 1000);
    }
    /**
     * 哭脸吐司
     * @param content 内容
     */
    public static void setErrorToast(Context context,Spanned content){
       SetToast(context ,R.drawable.base_toast_face_sad ,content , 1000);
    }
    /**
     * 笑脸吐司
     * @param content 文字
     */
    public static void setOKToast(Context context,Spanned content){
        SetToast(context ,R.drawable.base_toast_face_ok ,content , 1000);
    }

    /**
     * 取消吐司
     */
    public static void cancelToast(){
        if (toast!=null)
        toast.cancel();
    }
}
