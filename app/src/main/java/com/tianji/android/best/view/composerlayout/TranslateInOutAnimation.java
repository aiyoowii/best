package com.tianji.android.best.view.composerlayout;

import android.view.View;
import android.view.animation.TranslateAnimation;

/**
 * Created by Cegrano on 2015/8/5.
 */
public class TranslateInOutAnimation extends InOutAnimation {
    //需要知道当前起始点和终止点
    float inX, inY, outX, outY;

    public TranslateInOutAnimation(Direction dir, long duration, View aview[], float inX, float inY, float outX, float outY) {
        super(true);
        this.inX = inX;
        this.inY = inY;
        this.outX = outX;
        this.outY = outY;
        direction = dir;
        if (direction == Direction.IN) {//in
            addInAnimation(aview);
        } else if (direction == Direction.OUT) {//out
            addOutAnimation(aview);
        }
        setDuration(duration);
    }

    @Override
    protected void addInAnimation(final View aview[]) {
        TranslateAnimation anim = new TranslateAnimation(outX, inX, outY, inY);
        addAnimation(anim);
    }

    @Override
    protected void addOutAnimation(final View aview[]) {
        TranslateAnimation anim = new TranslateAnimation(inX, outX, inY, outY);
        addAnimation(anim);
    }
}
