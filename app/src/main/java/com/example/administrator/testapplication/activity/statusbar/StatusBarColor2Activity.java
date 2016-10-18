package com.example.administrator.testapplication.activity.statusbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.testapplication.R;
import com.example.administrator.testapplication.utils.CommonUtils;

/**
 * 改变状态栏 颜色
 * 第二种方案：状态栏透明化（此时布局会延伸到状态栏）；根布局android:fitsSystemWindows="true"使布局不延伸到状态栏；顶部添加一个view到状态栏的位置
 *
 * Created by lifengmei on 2016/10/11 10:17.
 */
public class StatusBarColor2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbarcolor2);
        statusBarTransparent();
        addStatusBarView();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            int statusHeight =  CommonUtils.getStatusBarHeight1(this);
            Log.i("StatusBarColor2Activity","状态栏高度是："+statusHeight);
        }
    }

    /***
     * 界面跳转
     * @param context
     * @return
     */
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,StatusBarColor2Activity.class);
        return intent;
    }
    /***
     * 创建view并添加到状态栏
     */
    private void addStatusBarView(){
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.color_808080));
        int statusHeight =  CommonUtils.getStatusBarHeight(this);
        Log.i("StatusBarColor2Activity","状态栏高度是："+statusHeight);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
        ViewGroup decorView = (ViewGroup) findViewById(android.R.id.content);
        decorView.addView(view,params);
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
