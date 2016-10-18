package com.example.administrator.testapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.testapplication.R;
import com.example.administrator.testapplication.view.HorizontalListView;

import org.w3c.dom.Text;

/**
 * Created by lifengmei on 2016/8/4 11:12.
 * 动画demo
 */
public class AnimationActivity extends Activity {

    private HorizontalListView horizontalListView;
    private TextView tvTitle;
    private TextView tvTitleLeft;
    private Button btnStart;
    private Button btnStart1;
    private HorizontalListView horizontalListView1;
    private HorizontalListView horizontalListView2;
    private Button btnStart2;
    private Button btnStart3;
    private TextView tvA;
    private TextView tvB;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initView();
        initListener();

    }

    private void initView() {
        tvTitleLeft = (TextView) findViewById(R.id.titlebar_tv_left);
        tvTitle = (TextView) findViewById(R.id.titlebar_tv_title);
        btnStart = (Button) findViewById(R.id.animation_btn_start);
        horizontalListView = (HorizontalListView) findViewById(R.id.animation_horizontal_lv);
        horizontalListView.setAdapter(new MyAdapter());
        btnStart1 = (Button) findViewById(R.id.animation_btn_start1);
        horizontalListView1 = (HorizontalListView) findViewById(R.id.animation_horizontal_lv1);
        horizontalListView2 = (HorizontalListView) findViewById(R.id.animation_horizontal_lv2);
        btnStart2 = (Button) findViewById(R.id.animation_btn_start2);
        btnStart3 = (Button) findViewById(R.id.animation_btn_start3);
        tvA = (TextView) findViewById(R.id.animation_tv_a);
        tvB = (TextView) findViewById(R.id.animation_tv_b);

    }


    private void initListener() {
        MyAdapter1 myAdapter1 = new MyAdapter1();
        horizontalListView1.setAdapter(myAdapter1);
        MyAdapter myAdapter2 = new MyAdapter();
        horizontalListView2.setAdapter(myAdapter2);
        horizontalListView2.setVisibility(View.INVISIBLE);

//        horizontalListView1.post(new Runnable() {
//            @Override
//            public void run() {
//                View view = horizontalListView1.getChildAt(0);
//                if(view!=null){
//
//                    view.clearAnimation();
//                }
//            }
//        });
        tvTitle.setText("动画演示");
        tvTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //第一个横向listview平移进入
        horizontalListView.setVisibility(View.INVISIBLE);
        final AnimationSet animationSet = new AnimationSet(true);
        //参数1-2 X轴开始位置；3-4Y轴开始位置；5-6X轴结束位置；7-8Y轴结束位置
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horizontalListView.setVisibility(View.VISIBLE);
                horizontalListView.startAnimation(animationSet);
            }
        });

        //横向listview淡入淡出
        final AnimationSet animationSet1 = new AnimationSet(true);
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(1,0);
        alphaAnimation1.setDuration(500);
        alphaAnimation1.setFillAfter(true);
        animationSet1.setStartOffset(0);
        animationSet1.setFillAfter(true);
        animationSet1.addAnimation(alphaAnimation1);
        final AnimationSet animationSet2 = new AnimationSet(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0,1);
        alphaAnimation2.setDuration(500);
        alphaAnimation2.setFillAfter(true);
        animationSet2.setFillAfter(true);
        animationSet2.setStartOffset(500);
        animationSet2.addAnimation(alphaAnimation2);
        flag = 1;
        btnStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (/*horizontalListView1.getVisibility() == View.VISIBLE*/flag ==1) {
                    flag = 2;
                    horizontalListView1.startAnimation(animationSet1);
                    horizontalListView2.startAnimation(animationSet2);
                   //horizontalListView1.setVisibility(View.INVISIBLE);
                } else {
                    flag = 1;
                    horizontalListView2.startAnimation(animationSet1);
                    horizontalListView1.startAnimation(animationSet2);
                    //horizontalListView1.setVisibility(View.VISIBLE);
                }
            }
        });

        //缩放动画
        final AnimationSet sAnimationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation0 = new ScaleAnimation(1f,0.1f,1f,1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation0.setDuration(500);
        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1f,2f,1f,2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation1.setDuration(500);
        scaleAnimation1.setStartOffset(500);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -2f);
        translateAnimation1.setDuration(500);
        sAnimationSet.addAnimation(scaleAnimation0);
//        sAnimationSet.addAnimation(translateAnimation1);
//        sAnimationSet.addAnimation(scaleAnimation1);
        sAnimationSet.setFillAfter(true);
        btnStart3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart3.startAnimation(sAnimationSet);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        View view = horizontalListView1.getChildAt(1);
        if (view != null) {

            view.clearAnimation();
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(AnimationActivity.this).inflate(R.layout.item_animation, null);
                holder.tvContent = (TextView) convertView.findViewById(R.id.item_animation_tv_content);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvContent.setText("隐藏内容" + position);
            return convertView;
        }
    }

    private class MyAdapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(AnimationActivity.this).inflate(R.layout.item_animation, null);
                holder.tvContent = (TextView) convertView.findViewById(R.id.item_animation_tv_content);
                convertView.setTag(holder);
                final AnimationSet animationSet = new AnimationSet(true);
                //参数1-2 X轴开始位置；3-4Y轴开始位置；5-6X轴结束位置；7-8Y轴结束位置
                final TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 4f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f,
                        Animation.RELATIVE_TO_SELF, 0f);
                translateAnimation.setDuration(1000);
//                translateAnimation.setFillAfter(true);
                //            translateAnimation.setRepeatMode(Animation.REVERSE);
                //            translateAnimation.setRepeatCount(1);
                switch (position) {
                    case 0:
                        translateAnimation.setStartOffset(0);
                        animationSet.addAnimation(translateAnimation);
                        convertView.setAnimation(animationSet);
                        break;
                    case 1:
                        translateAnimation.setStartOffset(50);
                        animationSet.addAnimation(translateAnimation);
                        convertView.setAnimation(animationSet);
                        break;
                    case 2:
                        translateAnimation.setStartOffset(100);
                        animationSet.addAnimation(translateAnimation);
                        convertView.setAnimation(animationSet);
                        break;
                    case 3:
                        translateAnimation.setStartOffset(150);
                        animationSet.addAnimation(translateAnimation);
                        convertView.setAnimation(animationSet);
                        break;
                }

            } else {
                holder = (ViewHolder) convertView.getTag();
//                convertView.clearAnimation();
            }
            holder.tvContent.setText("内容" + position);
            return convertView;
        }
    }

    private class ViewHolder {
        private TextView tvContent;
    }
}
