package com.example.administrator.propertyanimation_2;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * Created by Bin(agnesjiang@qq.com) on 2015/8/3.
 */
public class ColorEvaluator implements TypeEvaluator {
    private int mCurrentRed = -1;
    private int mCurrentGreen = -1;
    private int mCurrentBlue = -1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;
        int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);
        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);

        //初始值
        if (mCurrentRed == -1) {
            mCurrentRed = startRed;
        }
        if (mCurrentGreen == -1) {
            mCurrentGreen = startGreen;
        }
        if (mCurrentBlue == -1) {
            mCurrentBlue = startBlue;
        }

        //差值
        int redDiff = Math.abs(endRed - startRed);
        int greenDiff = Math.abs(endGreen - startGreen);
        int blueDiff = Math.abs(endBlue - startBlue);
        int colorDiff = redDiff + greenDiff + blueDiff;

        if (mCurrentRed != endRed) {
            mCurrentRed = getCurrentColor(startRed, endRed, fraction);
        }
        if (mCurrentGreen != endGreen) {
            mCurrentGreen = getCurrentColor(startGreen, endGreen, fraction);
        }
        if (mCurrentBlue != endBlue) {
            mCurrentBlue = getCurrentColor(startBlue, endBlue, fraction);
        }
        Log.v("currendColor", mCurrentRed+":"+mCurrentGreen+":"+mCurrentBlue);

        String currentColor =
                "#"
                        + getHaxStringByDecimal(mCurrentRed)
                        + getHaxStringByDecimal(mCurrentGreen)
                        + getHaxStringByDecimal(mCurrentBlue);
        return currentColor;
    }

    //获取三原色变化
    private int getCurrentColor(int startColor, int endColor, float fraction) {
        int currentColor;
        if (startColor < endColor) {
            currentColor = (int) (startColor + fraction * (endColor - startColor));
        } else {
            currentColor = (int) (startColor - fraction * (startColor - endColor));
        }
        return currentColor;
    }

    //获取10→16进制值
    private String getHaxStringByDecimal(int value) {
        String hex = Integer.toHexString(value);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }
        return hex;
    }
}
