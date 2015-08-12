package com.tianji.android.best.view.rulerlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by Cegrano on 2015/8/12.
 */
public class HorizontalRulerView extends HorizontalScrollView {

    private OnScrollListener onScrollListener;

    public HorizontalRulerView(Context context) {
        super(context);
    }

    public HorizontalRulerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HorizontalRulerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener!=null)
            onScrollListener.onScroll(l, t, oldl, oldt);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public interface OnScrollListener{
        void onScroll(int l, int t, int oldl, int oldt);
    }
}
