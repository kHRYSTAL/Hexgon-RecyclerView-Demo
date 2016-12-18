package me.khrystal.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/19
 * update time:
 * email: 723526676@qq.com
 */

public class HiveLayoutManager extends GridLayoutManager {

    @Override
    public void removeAndRecycleViewAt(int index, RecyclerView.Recycler recycler) {
        // do nothing
    }

    public HiveLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public HiveLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public HiveLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void removeAndRecycleView(View child, RecyclerView.Recycler recycler) {
        // do nothing
    }


}
