package com.tianji.android.best.service.net;

/**
 * Created by cegrano on 15/8/10.
 * 用以判断当前网络请求是那个
 */
public enum ParsingType {
    FEED_NEARBY,
    FEED_FRIENDS,
    FEED_HOT;

    public static String getUrl(ParsingType type){
        switch (type){
            case FEED_NEARBY:
                return "";
            case FEED_FRIENDS:
                return "";
            case FEED_HOT:
                return "";
            default:
                return "";
        }
    }
}
