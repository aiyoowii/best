package com.tianji.android.best.service.event;

import com.google.common.eventbus.EventBus;

/**
 * Created by Cegrano on 2015/8/11.
 */
public class EventBusHelper {
    private static EventBus INSTANCE;

    public synchronized  static void init() {
        if (INSTANCE == null) {
            synchronized (new Object()) {
                if (INSTANCE == null)
                    INSTANCE = new EventBus();
            }
        }
    }

    public static EventBus getEventBus() {
        if (INSTANCE == null)
            init();
        return INSTANCE;
    }
}
