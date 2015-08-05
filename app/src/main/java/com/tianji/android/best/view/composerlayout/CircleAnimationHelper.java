package com.tianji.android.best.view.composerlayout;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cegrano on 2015/8/5.
 */
public class CircleAnimationHelper extends InOutAnimationHelper {
    private float startDegree = -90F,endDegree = 90F;
    private float inX,inY,outX,outY;
    private float distance;
    public CircleAnimationHelper(ViewGroup viewGroup, boolean isOut,float distance) {
        super(viewGroup, isOut);
        this.distance = distance;
    }
    public CircleAnimationHelper(ViewGroup viewGroup,float distance) {
        super(viewGroup, false);
        this.distance = distance;
    }
    public CircleAnimationHelper(ViewGroup viewGroup, boolean isOut,float distance,float inX,float inY,float startDegree,float endDegree) {
        this(viewGroup, isOut, distance);
        this.inX = inX;
        this.inY = inY;
        this.startDegree = startDegree;
        this.endDegree = endDegree;
    }

    @Override
    protected InOutAnimation getInOutAnimation(InOutAnimation.Direction direction, int duration, ViewGroup ViewGroup, int index) {
        int count = viewGroup.getChildCount();
        float degree = (endDegree - startDegree)/(count-1) * index + startDegree;
        outX = (float) (inX + distance * Math.sin(degree));
        outY = (float) Math.abs(inY + distance * Math.cos(degree));
        return new TranslateInOutAnimation(direction,duration,new View[]{viewGroup.getChildAt(index)},inX,inY,outX,outY);
    }
}
