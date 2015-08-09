package com.tianji.android.best.ui.start;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.tianji.android.best.R;

/**
 * Created by cegrano on 15/8/9.
 */
public class StartActivity extends Activity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        checkDataUpdate();
        jumpToNextActivity();
    }

    private void jumpToNextActivity() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //todo 判断跳登录，引导，活动，还是主页
            }
        }, 3000);
    }

    //todo 发送网络请求，更新本地一些持久化的缓存
    private void checkDataUpdate() {

    }
}
