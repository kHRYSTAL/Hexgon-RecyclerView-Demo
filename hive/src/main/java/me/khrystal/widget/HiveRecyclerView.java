package me.khrystal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import static android.R.attr.orientation;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/14
 * update time:
 * email: 723526676@qq.com
 */

public class HiveRecyclerView extends RecyclerView {

    private static final int SIZE= 2;
    private static final int DEFAULT_ORIENTATION = 0;

    private Context mContext;

    /** horizontal spacing */
    private float mHorizontalSpacing;

    /** vertical spacing */
    private float mVerticalSpacing;

    /** row size */
    private int mRowSize;

    /** orientation */
    private int mOrientation;

    public HiveRecyclerView(Context context) {
        this(context, null);
    }

    public HiveRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public HiveRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        setMinimumWidth(width - 30);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HiveRecyclerView);
        mHorizontalSpacing = ta.getDimension(R.styleable.HiveRecyclerView_hive_horizontal_spacing, 0.0f);
        mVerticalSpacing = ta.getDimension(R.styleable.HiveRecyclerView_hive_vertical_spacing, 0.0f);
        mRowSize = ta.getInt(R.styleable.HiveRecyclerView_items_count_in_row, SIZE);
        mOrientation = ta.getInt(R.styleable.HiveRecyclerView_orientation, DEFAULT_ORIENTATION);
        ta.recycle();
        if (mRowSize < 2)
            throw new RuntimeException("Hexagon RecyclerView row_size can't be smaller than 2");
        final int itemsInTwoRows = mRowSize * 2 - 1;
        final int itemsCountInSmallRow = mRowSize - 1;

        GridLayoutManager mLayoutManager = new GridLayoutManager(context, mRowSize * itemsCountInSmallRow) {

            @Override
            protected int getExtraLayoutSpace(State state) {
                return 300;
            }
        };

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ((position % itemsInTwoRows >= 0) && (position % itemsInTwoRows <= itemsCountInSmallRow - 1))
                    return mRowSize;
                return itemsCountInSmallRow;
            }
        });

        setLayoutManager(mLayoutManager);
        if (orientation == 0) {
            mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            addItemDecoration(new HorizontalOverlapDecorator(mRowSize, mHorizontalSpacing, mVerticalSpacing));
        } else {
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            addItemDecoration(new VerticalOverlapDecorator(mRowSize, mHorizontalSpacing, mVerticalSpacing));
        }

    }
}
