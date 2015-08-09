package com.tianji.android.best.ui.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.tianji.android.best.utils.ViewMappingUtil;

/**
 * Created by Cegrano on 2015/8/4.
 */
public class BaseActivity extends FragmentActivity {
    public boolean onJumpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewMappingUtil.mapView(this,this,true);
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
