package com.example.administrator.testapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.testapplication.R;

/**
 * Created by lifengmei on 2016/3/24 10:22.
 * 裁剪框上面的圆形镂空遮罩
 */
public class CircleZhezhaoView extends ImageView {

    /**镂空圆的半径*/
    private float radius;
    /**镂空圆的边框宽度*/
    private float boarderWidth;
    /**屏幕的宽**/
    private int width;
    /**屏幕的高**/
    private int height;
    private Paint paint;
    /**遮罩颜色*/
    private int backgroundColor;
    /**获取镂空圆边框颜色*/
    private int circleStrokeColor;
    /**镂空图方形的左上和宽高*/
    private float circleLeft,circleTop,circleWidth,circleHeight;
    /**镂空圆的中心x*/
    private float circleX;
    /**镂空圆的中心y*/
    private float circleY;

    public CircleZhezhaoView(Context context) {
        this(context, null);

    }
    public CircleZhezhaoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }
    public CircleZhezhaoView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleZhezhaoView, defStyle, 0);
        //获取遮罩背景色
        backgroundColor = attributes.getColor(R.styleable.CircleZhezhaoView_backgroud_color, getResources().getColor(R.color.color_66000000));
        //获取镂空圆边框颜色
        circleStrokeColor = attributes.getColor(R.styleable.CircleZhezhaoView_backgroud_color, Color.WHITE);
        radius = attributes.getDimensionPixelOffset(R.styleable.CircleZhezhaoView_circle_radius, 200);
        boarderWidth = attributes.getDimensionPixelOffset(R.styleable.CircleZhezhaoView_circle_stroke_width, 2);
        paint = new Paint();
        paint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        circleX = width/(float)2;
        circleY = height/(float)2;
        circleLeft = circleX - radius;
        circleTop = circleY - radius;
        circleWidth = circleHeight = radius * 2;
        setCircleLeft(circleLeft);
        setCircleTop(circleTop);
        setCircleWidth(circleWidth);
        setCircleHeight(circleHeight);


        //画透明遮罩
        paint.setColor(backgroundColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, width, height, paint);

        //制造中间的镂空
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        //开启硬件加速 交集才能生效，才能做出遮罩效果
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //取非交集底层图片
        Xfermode xFermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        paint.setXfermode(xFermode);
        canvas.drawCircle(circleX, circleY, radius, paint);
        //给镂空圆形画白边
        paint.setColor(Color.WHITE);
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.STROKE);//圆环
        paint.setStrokeWidth(boarderWidth);
        canvas.drawCircle(circleX, circleY,radius,paint);
    }

    public float getCircleLeft() {
        return circleLeft;
    }

    /***
     * 设置镂空圆所在矩形的左x坐标
     * @param circleLeft
     */
    public void setCircleLeft(float circleLeft) {
        this.circleLeft = circleLeft;
    }

    public float getCircleTop() {
        return circleTop;
    }

    /***
     * 设置镂空圆所在矩形的上y坐标
     * @param circleTop
     */
    public void setCircleTop(float circleTop) {
        this.circleTop = circleTop;
    }

    public float getCircleWidth() {
        return circleWidth;
    }

    /***
     * 设置镂空圆所在矩形的宽
     * @param circleWidth
     */
    public void setCircleWidth(float circleWidth) {
        this.circleWidth = circleWidth;
    }

    public float getCircleHeight() {
        return circleHeight;
    }

    /***
     * 设置镂空圆所在矩形的高
     * @param circleHeight
     */
    public void setCircleHeight(float circleHeight) {
        this.circleHeight = circleHeight;
    }
}
