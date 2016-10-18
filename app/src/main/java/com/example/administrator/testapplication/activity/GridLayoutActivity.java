package com.example.administrator.testapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.administrator.testapplication.R;
import com.example.administrator.testapplication.view.ImageDisplayViewGroup;
import com.example.administrator.testapplication.view.MyViewGroup;

/**
 * Created by lifengmei on 2016/5/4 10:23.
 */
public class GridLayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayout);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.hx_keeper_gridLayout);
        for (int i = 0; i<8;i++){

            TextView textView = new TextView(this);
            textView.setText("就你能就你能就你能");
            gridLayout.addView(textView);
            gridLayout.setUseDefaultMargins(true);
        }
//        ImageDisplayViewGroup imageDisplayViewGroup = (ImageDisplayViewGroup) findViewById(R.id.displayviewgroup);
//        for (int i = 0; i<8;i++){
//
//            TextView textView = new TextView(this);
//            textView.setText("eee额度三季度降水量大飞机");
//            imageDisplayViewGroup.addView(textView);
//        }
        MyViewGroup myviewgroup = (MyViewGroup) findViewById(R.id.myviewgroup);
        TextView textView = new TextView(this);
        textView.setText("哈哈");
        textView.setPadding(5,5,5,5);
        myviewgroup.addView(textView);
        TextView textView1 = new TextView(this);
        textView1.setText("你说什么");
        textView1.setPadding(5,5,5,5);
        myviewgroup.addView(textView1);
        TextView textView2 = new TextView(this);
        textView2.setText("我说那边有穿吗");
        textView2.setPadding(5,5,5,5);
        myviewgroup.addView(textView2);
        TextView textView3 = new TextView(this);
        textView3.setText("eee额度三季度降水量大飞机");
        textView3.setPadding(5,5,5,5);
        myviewgroup.addView(textView3);
        for (int i = 0; i<8;i++){

            TextView textView4 = new TextView(this);
            textView4.setText("名表");
            textView4.setPadding(5,5,5,5);
            myviewgroup.addView(textView4);
        }
    }
}
