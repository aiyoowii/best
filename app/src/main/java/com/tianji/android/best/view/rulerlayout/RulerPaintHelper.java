package com.tianji.android.best.view.rulerlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * Created by Cegrano on 2015/8/10.
 */
public class RulerPaintHelper implements BasePaintHelper{

    public static final int LARGE = 0;
    public static final int Mid = 1;
    public static final int SMALL = 2;
    public static final int SPACE = 3;
    public static final int TEXT = 4;

    public float smallLineWidth = 1;
    public float smallLineHeight = 10;
    public float midLineWidth = 1.5f;
    public float midLineHeight = 15;
    public float bigLineWidth = 2;
    public float bigLineHeight = 20;
    public float spaceWidth = 5;
    public float spaceHeight = 0;
    public int textSize = 11;
    public int lineColor = Color.WHITE;
    public int textColor = Color.WHITE;

    public RulerPaintHelper() {
    }

    public RulerPaintHelper(Context context,AttributeSet attrs) {
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i=0;i<100;i++) {
            canvas.drawLine(i * 30, canvas.getHeight(), i * 30, canvas.getHeight()-30, getPaint(Mid));
        }
//        canvas.drawCircle(100,100,100,getPaint(LARGE));
    }

    public Paint getPaint(int index) {
        Paint paint = new Paint();
        paint.setColor(lineColor);
        paint.setAntiAlias(true);
        switch (index) {
            case LARGE:
                paint.setStrokeWidth(bigLineWidth);
                break;
            case Mid:
                paint.setStrokeWidth(midLineWidth);
                break;
            case SMALL:
                paint.setStrokeWidth(smallLineWidth);
                break;
            case SPACE:
                paint.setStrokeWidth(0);
                paint.setColor(Color.TRANSPARENT);
            case TEXT:
            default:
                paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
                paint.setTextSize(textSize);
                paint.setColor(textColor);
                paint.setTextAlign(Paint.Align.CENTER);
                break;
        }
        return paint;
    }
}
