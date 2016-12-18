package me.khrystal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/17
 * update time:
 * email: 723526676@qq.com
 */

public class VerticalHiveLayout extends HiveLayout {

    public VerticalHiveLayout(Context context) {
        super(context);
    }

    public VerticalHiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalHiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VerticalHiveLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        double height = 1.1547005383 * width; //(sqrt(3)/2)
        setMeasuredDimension(width, (int) height);
    }
}
