package com.tianji.android.best.ui.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.tianji.android.best.utils.ViewMappingUtil;

/**
 * Created by Cegrano on 2015/8/4.
 */
public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewMappingUtil.mapView(this,this,true);
    }
}
