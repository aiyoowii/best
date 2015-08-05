package com.tianji.android.best.view.composerlayout;

import android.view.View;
import android.view.animation.AnimationSet;

/**
 * Created by Cegrano on 2015/8/5.
 */
public abstract class InOutAnimation extends AnimationSet {
    public Direction direction;

    public static enum  Direction
    {
        IN,
        OUT,
    }

    protected InOutAnimation(boolean shareInterpolator) {
        super(shareInterpolator);
    }

    public InOutAnimation(Direction dir,long duration,View aview[]){
        super(true);
        direction = dir;
        if(direction == Direction.IN){//in
            addInAnimation(aview);
        } else if(direction == Direction.OUT){//out
            addOutAnimation(aview);
        }
        setDuration(duration);
    }

    protected abstract void addInAnimation(View aview[]);

    protected abstract void addOutAnimation(View aview[]);
}
