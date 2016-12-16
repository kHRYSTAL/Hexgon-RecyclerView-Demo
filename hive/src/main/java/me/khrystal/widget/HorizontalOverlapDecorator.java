package me.khrystal.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/14
 * update time:
 * email: 723526676@qq.com
 */

public class HorizontalOverlapDecorator extends RecyclerView.ItemDecoration {

    private int mSpaceHeight;
    private int mSpaceWidth;
    private final int mRowSize;

    public HorizontalOverlapDecorator(int rowSize, float mSpaceWidth, float mSpaceHeight) {
        this.mSpaceHeight = (int) mSpaceHeight;
        this.mSpaceWidth = (int) mSpaceWidth;
        this.mRowSize = rowSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        int itemsInTwoRows = mRowSize * 2 - 1;
        int itemsCountInSmallRow = mRowSize - 1;
        int smallRowPaddingTopBottom = parent.getHeight() / (mRowSize * 2);
        int smallRowPaddingItemSqueez = parent.getHeight() / (mRowSize * (itemsCountInSmallRow));
        int itemRowShift = -1 * (int) (smallRowPaddingTopBottom / (Math.sqrt(3))) + mSpaceWidth;

        double offsetUp = 0;
        double offsetDown = 0;
        double offsetLeft = position == 0 ? 0 : itemRowShift * (mRowSize + 2) / (mRowSize + 1);
        double offsetRight = 0;

        double shiftFactor = 1.5 * itemsCountInSmallRow - mRowSize;

        if (((position % itemsInTwoRows) >= 0) && ((position % itemsInTwoRows) < itemsCountInSmallRow)) {
            offsetUp = (shiftFactor - ((position % itemsInTwoRows) - 1)) * smallRowPaddingItemSqueez;
            offsetDown = ((position % itemsInTwoRows) - shiftFactor) * smallRowPaddingItemSqueez;
        }

        if ((position % itemsInTwoRows) == 0) {
            offsetUp = smallRowPaddingTopBottom ;
        }
        if ((position % itemsInTwoRows) == itemsCountInSmallRow - 1) {
            offsetDown = smallRowPaddingTopBottom;

        }

        outRect.set((int) Math.ceil(offsetLeft),
                (int) Math.ceil(offsetUp + (smallRowPaddingItemSqueez / (2 * mRowSize + 1)) + mSpaceHeight / 2),
                (int) Math.ceil(offsetRight),
                (int) Math.ceil(offsetDown + (smallRowPaddingItemSqueez / (2 * mRowSize + 1)) + mSpaceHeight / 2));

    }
}