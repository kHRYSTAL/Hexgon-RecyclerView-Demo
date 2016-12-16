package me.khrystal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/15
 * update time:
 * email: 723526676@qq.com
 */

public class HiveLayout extends FrameLayout {

    private static final int MAX_CLICK_DURATION = 1000;
    private static final int MAX_CLICK_DISTANCE = 15;

    private long pressStartTime;

    private float pressedX;

    private float pressedY;

    private boolean stayedWithinClickDistance;

    public HiveLayout(Context context) {
        super(context);
    }

    public HiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HiveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HiveLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                pressStartTime = System.currentTimeMillis();
                pressedX = event.getX();
                pressedY = event.getY();
                stayedWithinClickDistance = true;
                return checkIfClickInsideTheCircle(event.getX(), event.getY());
            }
            case MotionEvent.ACTION_MOVE: {
                if (stayedWithinClickDistance && distance(pressedX, pressedY, event.getX(), event.getY()) > MAX_CLICK_DISTANCE) {
                    stayedWithinClickDistance = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                long pressDuration = System.currentTimeMillis() - pressStartTime;
                if (pressDuration < MAX_CLICK_DURATION && stayedWithinClickDistance) {
                    // Click event has occurred
                    boolean inside = checkIfClickInsideTheCircle(event.getX(), event.getY());
                    if (inside)
                        performClick();
                    return inside;
                }
            }
        }
        return false;
    }

    private boolean checkIfClickInsideTheCircle(float touchX, float touchY) {
        float centerX, centerY, radius;
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = centerX - 20;
        if (Math.pow(touchX - centerX, 2)
                + Math.pow(touchY - centerY, 2) < Math.pow(radius, 2)) {
            return true;
        } else {
            return false;
        }
    }

    private float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float distanceInPx = (float) Math.sqrt(dx * dx + dy * dy);
        return px2dp(distanceInPx);
    }

    private float px2dp(float px) {
        return px / getResources().getDisplayMetrics().density;
    }
}
