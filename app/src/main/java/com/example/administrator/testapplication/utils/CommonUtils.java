package com.example.administrator.testapplication.utils;

import android.app.Activity;
import android.graphics.Rect;

import java.lang.reflect.Field;

/**
 * Created by lifengmei on 2016/10/11 14:29.
 */
public class CommonUtils {
    public static int STATUS_BAR_HEIGHT;


    /***
     * 获取状态栏高度
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity){
        if(STATUS_BAR_HEIGHT != 0){
            return STATUS_BAR_HEIGHT;
        }
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try{
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            STATUS_BAR_HEIGHT = activity.getResources().getDimensionPixelSize(x);
        }catch (Exception e){
            e.printStackTrace();
        }
        return STATUS_BAR_HEIGHT;
    }

    /***
     * 获取状态栏高度--此方法需要在onWindowFocusChanged的hasFocus时使用才有值
     * @param activity
     * @return
     */
    public static int getStatusBarHeight1(Activity activity){
        Rect ourRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(ourRect);
        return ourRect.top;
    }
}
