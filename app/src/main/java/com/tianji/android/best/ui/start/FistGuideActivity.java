package com.tianji.android.best.ui.start;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tianji.android.best.R;

/**
 * Created by cegrano on 15/8/9.
 */
public class FistGuideActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_guide);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_best:
                //todo 判断跳转
                break;
        }
    }
}
