package com.tianji.android.best.view.rulerlayout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.tianji.android.best.R;
import com.tianji.android.best.utils.ToastUtil;

public class RulerTestActivity extends ActionBarActivity {

    private View rulerView;
    private HorizontalRulerView scrollView;
    private TextView valueView;
    private double startValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler_test);
        rulerView = findViewById(R.id.ruler);
        scrollView = (HorizontalRulerView) findViewById(R.id.ruler_scroller);
        valueView = (TextView) findViewById(R.id.ruler_value);
        rulerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                rulerView.removeOnLayoutChangeListener(this);
                startValue = (rulerView.getWidth() - rulerView.getPaddingRight() - rulerView.getPaddingLeft())/2d;
                valueView.setText(String.format("%.2f",startValue/30f)+"cm");
            }
        });

        scrollView.setOnScrollListener(new HorizontalRulerView.OnScrollListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
//                ToastUtil.getInstance().makeToast(l+"|"+t+"|"+oldl+"|"+oldt+"|");
                valueView.setText(String.format("%.2f",(startValue+l)/30f)+"cm");
            }
        });
    }

}
