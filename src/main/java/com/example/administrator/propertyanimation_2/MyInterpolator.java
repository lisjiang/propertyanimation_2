package com.example.administrator.propertyanimation_2;

import android.animation.TimeInterpolator;

/**
 * Created by Bin(agnesjiang@qq.com) on 2015/8/3.
 * 自定义控制动画的变化速率
 * 先加速后加速
 */
public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float value;
        if (input<0.5){
            value= (float) (Math.sin(Math.PI*input)/2);
        }else {
            value= (float) ((2-Math.sin(Math.PI*input))/2);
        }
        return value;
    }
}
