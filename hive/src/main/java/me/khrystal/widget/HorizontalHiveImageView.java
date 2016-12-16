package me.khrystal.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.github.siyamed.shapeimageview.ShaderImageView;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
import com.github.siyamed.shapeimageview.shader.SvgShader;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/15
 * update time:
 * email: 723526676@qq.com
 */

public class HorizontalHiveImageView extends ShaderImageView {

    public HorizontalHiveImageView(Context context) {
        super(context);
    }

    public HorizontalHiveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalHiveImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.horizontal_hive);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        double width = 1.1547005383 * height; //(sqrt(3)/2)
//        int width = MeasureSpec.getSize(heightMeasureSpec);
//        double height = 1.1547005383 * width;
        setMeasuredDimension((int) width, (int) height);
    }


}
