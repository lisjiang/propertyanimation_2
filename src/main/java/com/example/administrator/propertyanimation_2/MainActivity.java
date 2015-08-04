package com.example.administrator.propertyanimation_2;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.BounceInterpolator;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private MyView myView;
    private static float RADIUS = 50;
    private MyPoint currentPoint;
    private float screenWeigh;
    private float screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        moveMyView();
        testViewPropertyAnimator();

    }

    /**
     * ViewPropertyAnimator的用法
     */
    private void testViewPropertyAnimator() {
        myView.animate().x(100).y(100)
                .setInterpolator(new BounceInterpolator())
                .alpha(0f).setDuration(3000);
    }

    private void init() {
        myView = (MyView) findViewById(R.id.myView);
        screenWeigh = MyScreenUtils.getScreenWeigh(getApplication());
        screenHeight = MyScreenUtils.getScreenHight(getApplication());
    }

    /**
     * ValueAnimator、自定义ObjectAnimator、Interpolator的用法
     */
    private void moveMyView() {

        MyPoint startPoint = new MyPoint(screenWeigh / 2, RADIUS);
        MyPoint endPoint = new MyPoint(screenWeigh / 2,
                screenHeight - RADIUS - 500);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (MyPoint) animation.getAnimatedValue();
                myView.setMyPoint(currentPoint);
                myView.invalidate();
            }
        });

        //位移
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Toast.makeText(getApplicationContext(), "动画开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(getApplicationContext(), "动画结束", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        /*
            AccelerateInterpolator、BounceInterpolator:动画补间器
            BounceInterpolator就是一种可以模拟物理规律，实现反复弹起效果的Interpolator
         */
        //        valueAnimator.setInterpolator(new AccelerateInterpolator(2f));
        //        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setInterpolator(new MyInterpolator());

        valueAnimator.setRepeatCount(1);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

        //颜色变化
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(myView, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofObject(myView, "color", new ColorEvaluator(), "#FF0000", "#0000FF");

        //组合动画
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.play(objectAnimator1).after(objectAnimator);
        animatorSet1.setDuration(2500);

        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(valueAnimator).with(animatorSet1);
        animatorSet2.setDuration(2500);

        animatorSet2.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
