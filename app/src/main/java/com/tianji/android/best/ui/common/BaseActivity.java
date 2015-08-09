package com.tianji.android.best.ui.common;

import android.content.Intent;
import android.os.Bundle;

import com.tianji.android.best.utils.ViewMappingUtil;
import com.tianji.android.best.view.swipebacklayout.app.SwipeBackActivity;

/**
 * Created by Cegrano on 2015/8/4.
 * 只用于可以滑动返回的页面
 */
public class BaseActivity extends SwipeBackActivity {
    public boolean onJumpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewMappingUtil.mapView(this, this, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onJumpActivity = false;
    }

    public void startActivityImpl(Class<?> jumpClass) {
        startActivityImpl(new Intent(this, jumpClass));
    }

    public void startActivityImpl(Intent intent) {
        if (!onJumpActivity) {
            startActivity(intent);
            onJumpActivity = true;
        }
    }
}
