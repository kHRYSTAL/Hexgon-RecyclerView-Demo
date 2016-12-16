package me.khrystal.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/16
 * update time:
 * email: 723526676@qq.com
 */

public interface ItemViewMode {
    void applyToView(View v, RecyclerView parent);
}
