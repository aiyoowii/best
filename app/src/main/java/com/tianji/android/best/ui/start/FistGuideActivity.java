package com.tianji.android.best.ui.start;

import android.view.View;

import com.tianji.android.best.R;
import com.tianji.android.best.ui.common.BaseActivity;
import com.tianji.android.best.utils.ViewMapping;

/**
 * Created by cegrano on 15/8/9.
 */
@ViewMapping(id = R.layout.activity_first_guide)
public class FistGuideActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onStart() {
        super.onStart();

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
