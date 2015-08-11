package com.tianji.android.best.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.eventbus.EventBus;
import com.tianji.android.best.service.event.EventBusHelper;
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
        EventBusHelper.getEventBus().register(this);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBusHelper.getEventBus().unregister(this);
    }

    abstract protected int getContentViewRes();
}
