package com.tianji.android.best.service.event;

/**
 * Created by cegrano on 15/8/10.
 * 向监听者发送的event
 */
public class BaseEvent {
    protected EventType type;
    protected Object message;

    public BaseEvent(EventType type, Object message) {
        this.type = type;
        this.message = message;
    }

    public EventType getType() {
        return type;
    }

    public Object getMessage() {
        return message;
    }
}
