package com.tianji.android.best.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianji.android.best.utils.ViewMappingUtil;

/**
 * Created by Cegrano on 2015/8/4.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentViewRes(),container,false);
        ViewMappingUtil.mapView(this,view,true);
        return view;
    }

    abstract protected int getContentViewRes();
}
