package me.khrystal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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
    private int mRowSize = SIZE;

    /** orientation */
    private int mOrientation = DEFAULT_ORIENTATION;

    private ItemViewMode mItemViewMode;

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
        setMinimumWidth(width-30);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HiveRecyclerView);
        mHorizontalSpacing = ta.getDimension(R.styleable.HiveRecyclerView_hive_horizontal_spacing, 0.0f);
        mVerticalSpacing = ta.getDimension(R.styleable.HiveRecyclerView_hive_vertical_spacing, 0.0f);
        ta.recycle();

        final int itemsInTwoRows = mRowSize * 2 - 1;
        final int itemsCountInSmallRow = mRowSize - 1;

        GridLayoutManager mLayoutManager = new GridLayoutManager(context, mRowSize * itemsCountInSmallRow) {

            @Override
            protected int getExtraLayoutSpace(State state) {
                return 300;
            }
        } ;

        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if ((position % itemsInTwoRows >= 0) && (position % itemsInTwoRows <= itemsCountInSmallRow - 1)) {
                    Log.e("RowSize", "mRowSize:" + mRowSize);
                    return mRowSize;
                }
                Log.e("RowSize:", "itemsCountInSmallRow:" + itemsCountInSmallRow);
                return itemsCountInSmallRow;
            }
        });

        setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        addItemDecoration(new HorizontalOverlapDecorator(mRowSize, mHorizontalSpacing, mVerticalSpacing));
    }

    public void setItemViewMode(ItemViewMode itemViewMode) {
        mItemViewMode = itemViewMode;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mItemViewMode != null) {
            final int count = getChildCount();
            for (int i = 0; i < count; ++i) {
                View view = getChildAt(i);
                mItemViewMode.applyToView(view, this);
            }
        }
    }
}
