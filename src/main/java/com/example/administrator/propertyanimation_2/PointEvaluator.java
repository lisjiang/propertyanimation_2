package com.example.administrator.propertyanimation_2;

import android.animation.TypeEvaluator;

/**
 * Created by Bin(agnesjiang@qq.com) on 2015/7/21.
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        MyPoint startPoint = (MyPoint) startValue;
        MyPoint endPoint = (MyPoint) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        MyPoint currentPoint = new MyPoint(x, y);
        return currentPoint;
    }
}
