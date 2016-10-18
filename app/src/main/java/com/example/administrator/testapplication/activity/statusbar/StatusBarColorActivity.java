package com.example.administrator.testapplication.activity.statusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.example.administrator.testapplication.R;

/**
 * 改变状态栏 颜色
 * 第一种方案：状态栏透明化（此时布局会延伸到状态栏）；自定义title给一个状态栏高度的paddingtop；
 * 该方法的好处是可以自己控制title和状态栏的高度，但是要想适配所有状态栏都给系统给出的高度，则需要计算状态栏高度之后在设置自定义title的layoutparams中的paddingtop了
 * Created by lifengmei on 2016/10/11 10:17.
 */
public class StatusBarColorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbarcolor);
        statusBarTransparent();

    }


    public void solutionTwo(View v){
        startActivity(StatusBarColor2Activity.newIntent(this));
    }
    public void solutionThree(View v){
        startActivity(StatusBarColor3Activity.newIntent(this));
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
    //TODO  需研究的另一个问题，DrawerLayout + NavigationView + Toolbar，以及这些控件的使用
    //微信链接地址：http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=2650237284&idx=1&sn=6e3b30cc63d675bae8cb3e7c9563384e&chksm=8863980bbf14111d1e3ac9522ae56433a3486d58e7ad5b58c1894270a126823983fc3a6017ac&mpshare=1&scene=1&srcid=1010slRZNHSjYHwucuervtS5#rd
//    NavigationView 竟然没延伸到状态栏，好吧，继续修改，当系统版本小于5.0时，进行如下设置：
    DrawerLayout mDrawerLayout;//需要初始化
    private void navViewToTop(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            mDrawerLayout.setFitsSystemWindows(true);
            mDrawerLayout.setClipToPadding(false);
        }
    }
}
