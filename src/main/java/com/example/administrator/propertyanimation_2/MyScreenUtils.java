package com.example.administrator.propertyanimation_2;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by Bin(agnesjiang@qq.com) on 2015/6/25.
 */
public class MyScreenUtils {
    private static WindowManager windowManager;
    private float screenWeigh;
    private float screenHight;

    public static float getScreenWeigh(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getWidth();
    }

    public static float getScreenHight(Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay().getHeight();
    }
}
