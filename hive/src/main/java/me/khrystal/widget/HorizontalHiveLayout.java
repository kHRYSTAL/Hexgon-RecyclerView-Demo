package me.khrystal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/15
 * update time:
 * email: 723526676@qq.com
 */

public class HorizontalHiveLayout extends HiveLayout {

    private Object tag = null;

    public HorizontalHiveLayout(Context context) {
        super(context);
    }

    public HorizontalHiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalHiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HorizontalHiveLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        double width = 1.1547005383 * height; //(sqrt(3)/2)
        setMeasuredDimension((int) width, height);
    }
}
