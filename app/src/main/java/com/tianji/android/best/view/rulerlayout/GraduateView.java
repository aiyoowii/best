package com.tianji.android.best.view.rulerlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Cegrano on 2015/8/7.
 */
public class GraduateView extends View {
    private BasePaintHelper mPaintHelper = new RulerPaintHelper();
    private int mWidth;

    public GraduateView(Context context) {
        super(context);
    }

    public GraduateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraduateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaintHelper = new RulerPaintHelper(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GraduateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaintHelper = new RulerPaintHelper(context,attrs);
    }

    public void setmPaintHelper(BasePaintHelper mPaintHelper) {
        this.mPaintHelper = mPaintHelper;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mWidth ==0)
            return;
        drawGraduate(canvas);
        drawValue(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        mWidth = getWidth();
        super.onLayout(changed, left, top, right, bottom);
    }

    //画刻度值
    private void drawValue(Canvas canvas) {
        mPaintHelper.draw(canvas);
        setMinimumWidth(30*100);
        requestLayout();
    }

    //画刻度
    private void drawGraduate(Canvas canvas) {

    }
}
