package com.example.administrator.propertyanimation_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Bin(agnesjiang@qq.com) on 2015/7/21.
 */
public class MyView extends View {
    private MyPoint mMyPoint;
    private Paint mPaint;
    private String color;
    private static float RADIUS = 50;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            canvas.drawCircle(mMyPoint.getX(), mMyPoint.getY(), RADIUS, mPaint);

    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(10);
        if (mMyPoint == null) {
            mMyPoint = new MyPoint(RADIUS, RADIUS);
        }

    }

    public MyPoint getMyPoint() {
        return mMyPoint;
    }

    public void setMyPoint(MyPoint myPoint) {
        mMyPoint = myPoint;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        /*
        Color.parseColor(String string):
        Parse the color string, and return the corresponding color-int.
        */
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }
}
