package com.example.administrator.testapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.testapplication.R;
import com.example.administrator.testapplication.view.CircleZhezhaoView;

import java.io.File;

/**
 * Created by lifengmei on 2016/3/24 09:44.
 * 裁剪头像
 */
public class ClipImageViewNewActivity extends Activity implements View.OnClickListener ,View.OnTouchListener{
    private static final String INTENT_FROM = "intentfrom";
    private static final String INTENT_URI = "intenturi";
    private static final String INTENT_FILEPATH = "intentfilepath";
    private RelativeLayout rlTitleLeft;
    private ImageView ivTitleLeft;
    private TextView tvTitleName;
    private RelativeLayout rlTitleRight;
    private TextView tvTitleRight;
    private ImageView srcPic;
    private CircleZhezhaoView circleZhezhaoView;
    /**要展示的图片的bitmap*/
    private Bitmap mBitmap;

    private Matrix savedMatrix = new Matrix();
    private Matrix matrix = new Matrix();
    /** 动作标志：无 */
    private static final int NONE = 0;
    /** 动作标志：拖动 */
    private static final int DRAG = 1;
    /** 动作标志：缩放 */
    private static final int ZOOM = 2;
    /** 初始化动作标志 */
    private int mode = NONE;

    /** 记录起始坐标 */
    private PointF start = new PointF();
    /** 记录缩放时两指中间点坐标 */
    private PointF mid = new PointF();
    private float oldDist = 1f;
    /**缩放角度--暂时不用*/
    private float oldRotation = 0;

    /**拍照裁剪还是选相册裁剪 1拍照，2选相册*/
    private int from = 0;
    /**选相册裁剪传过来的uri*/
    private Uri uri;
    /**拍照裁剪传过来的路径*/
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        //initData();
        //initListener();

    }

    private void initView() {
        setContentView(R.layout.activity_cropimageview_new);
        srcPic = (ImageView)findViewById(R.id.cropimage);
        circleZhezhaoView = (CircleZhezhaoView) findViewById(R.id.circlezhe);
        rlTitleLeft = (RelativeLayout) findViewById(R.id.title_bar_rl_left);
        ivTitleLeft = (ImageView) findViewById(R.id.title_bar_iv_left);
        tvTitleName = (TextView) findViewById(R.id.title_bar_tv_title);
        rlTitleRight = (RelativeLayout) findViewById(R.id.title_bar_rl_right);
        tvTitleRight = (TextView) findViewById(R.id.title_bar_tv_right);
        tvTitleName.setText("裁剪");
        tvTitleRight.setText("保存");
        ivTitleLeft.setImageResource(R.mipmap.ic_launcher);

    }

    private void initData() {
        srcPic.post(new Runnable() {
            @Override
            public void run() {
                initImageViewSize();
            }
        });


    }
    private void initListener() {
        rlTitleLeft.setOnClickListener(this);
        rlTitleRight.setOnClickListener(this);
        srcPic.setOnTouchListener(this);
        srcPic.setScaleType(ImageView.ScaleType.MATRIX);
    }

    /**
     * 将图片根据ImagView的宽高展示在中央，类似于scaletype=center_inside
     */
    private void initImageViewSize(){
        //srcPic这个展示图片的ImageView的宽高
        float imageViewWidth = srcPic.getMeasuredWidth();
        float imageViewHeight =srcPic.getMeasuredHeight();
        //要放进去的图片的宽高
        int imageWidth = mBitmap.getWidth();
        int imageHeight = mBitmap.getHeight();
        // 按展示ImageView的大小该有的图片缩放比例
        float scale1 = (imageViewWidth * 1.0f) / imageWidth;
        float scale2 = (imageViewHeight * 1.0f) / imageHeight;
        float scale3 = Math.min(scale1, scale2);

        // 图片起始中心点
        float imageMidX = imageWidth / 2;
        float imageMidY = imageHeight / 2;
        //srcPic这个ImageView 的中心点--即图片的最终目的地中心点
        float midX = imageViewWidth / 2;
        float midY = imageViewHeight / 2;

        srcPic.setScaleType(ImageView.ScaleType.MATRIX);
        // 平移---从现在的图片中心到目标ImageView的中心
        matrix.postTranslate(midX-imageMidX, midY-imageMidY);
        // 缩放
        matrix.postScale(scale3, scale3, midX, midY);// 缩放
        srcPic.setImageMatrix(matrix);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.title_bar_rl_left:
                onBackPressed();
                break;
            case R.id.title_bar_rl_right:

                break;
        }

    }

    /**
     * 获取裁剪框内截图
     *
     * @return
     */
    private Bitmap getBitmap() {
        // 获取截屏
        View view = this.getWindow().getDecorView();
        srcPic.setDrawingCacheEnabled(true);
        srcPic.buildDrawingCache();
        //截屏时需要将title高度和状态栏高度计算进去
        Bitmap finalBitmap = Bitmap.createBitmap(srcPic.getDrawingCache(), (int) circleZhezhaoView.getCircleLeft(),
                (int) circleZhezhaoView.getCircleTop()/* + llTitlebar.getMeasuredHeight() + DeviceUtils.getStatusBarHeight(this)*/, (int) circleZhezhaoView.getCircleWidth(), (int) circleZhezhaoView.getCircleHeight());

        // 释放资源
        view.destroyDrawingCache();
        return finalBitmap;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                // 设置开始点位置
                start.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    //oldRotation = rotation(event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);// 平移
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    //float rotation = rotation(event) - oldRotation;//旋转，现在不加旋转
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);// 缩放
                        //matrix.postRotate(rotation, mid.x, mid.y);// 旋轉，现在不加旋转
                    }
                }
                break;
        }
        view.setImageMatrix(matrix);
        return true;
    }

    /**
     * 多点触控时，计算最先放下的两指距离
     *
     * @param event
     * @return
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 多点触控时，计算最先放下的两指中心坐标
     *
     * @param point
     * @param event
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    // 取旋转角度--需求不要旋转，此处先注释
//    private float rotation(MotionEvent event) {
//        double delta_x = (event.getX(0) - event.getX(1));
//        double delta_y = (event.getY(0) - event.getY(1));
//        double radians = Math.atan2(delta_y, delta_x);
//        return (float) Math.toDegrees(radians);
//    }
}
