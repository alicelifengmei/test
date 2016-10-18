package com.example.administrator.testapplication.activity.statusbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.administrator.testapplication.R;
import com.example.administrator.testapplication.utils.CommonUtils;

/**
 * 改变状态栏 颜色
 * 第二种方案：状态栏透明化（此时布局会延伸到状态栏）；最外层嵌套一个根布局并设置android:fitsSystemWindows="true"，并让背景色跟title背景色一致。
 * 此方案布局嵌套过多，不够优化，建议不使用
 *
 * Created by lifengmei on 2016/10/11 10:17.
 */
public class StatusBarColor3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbarcolor3);
        statusBarTransparent();

    }

    /***
     * 界面跳转
     * @param context
     * @return
     */
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,StatusBarColor3Activity.class);
        return intent;
    }

    /***
     * 状态栏透明化
     */
    private void statusBarTransparent(){
        //5.0及以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags;
        }
    }
}
