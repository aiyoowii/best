package com.tianji.android.best.view.composerlayout;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Cegrano on 2015/8/5.
 */
public abstract class InOutAnimationHelper {
    protected ViewGroup viewGroup;
    protected boolean isOut;

    public InOutAnimationHelper(ViewGroup viewGroup,boolean isOut){
        this.viewGroup = viewGroup;
        this.isOut = isOut;
    }
    public void toggle()
    {
        int count = viewGroup.getChildCount();
        for(int i=0; i<count; i++){
            View imgView = viewGroup.getChildAt(i);
            InOutAnimation animation = getInOutAnimation(isOut? InOutAnimation.Direction.IN: InOutAnimation.Direction.OUT, 200, viewGroup,i);
            long startOffset = i * 100 / (count -1);
            animation.setStartOffset(startOffset);
            animation.setInterpolator(new OvershootInterpolator(2F));
            animation.setFillAfter(true);
            imgView.startAnimation(animation);
        }
        isOut = !isOut;
    }

    public boolean isOut() {
        return isOut;
    }

    public void hide() {
        if (isOut)
            toggle();
    }

    protected abstract InOutAnimation getInOutAnimation(InOutAnimation.Direction direction, int duration, ViewGroup ViewGroup,int index);
}
