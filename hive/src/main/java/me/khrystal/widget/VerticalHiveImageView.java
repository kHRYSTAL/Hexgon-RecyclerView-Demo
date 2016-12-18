package me.khrystal.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.github.siyamed.shapeimageview.ShaderImageView;
import com.github.siyamed.shapeimageview.shader.ShaderHelper;
import com.github.siyamed.shapeimageview.shader.SvgShader;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/12/17
 * update time:
 * email: 723526676@qq.com
 */

public class VerticalHiveImageView extends ShaderImageView {
    public VerticalHiveImageView(Context context) {
        super(context);
    }

    public VerticalHiveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalHiveImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        double height = 1.1547005383 * width; //(sqrt(3)/2)
        setMeasuredDimension(width, (int) height);
    }


    @Override
    protected ShaderHelper createImageViewHelper() {
        return new SvgShader(R.raw.vertical_hive);
    }
}
