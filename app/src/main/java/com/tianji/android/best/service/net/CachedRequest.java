package com.tianji.android.best.service.net;

import android.util.SparseArray;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cegrano on 15/8/10.
 * 缓存的请求，所有需要在后台默默发起的请求
 */
public class CachedRequest {
    private static CachedRequest INSTANCE;
    Map<String,Cache> cachedRequests = new HashMap<>();

    public synchronized  static CachedRequest instance() {
        if (INSTANCE == null) {
            synchronized (new Object()) {
                if (INSTANCE == null)
                    INSTANCE = new CachedRequest();
            }
        }
        return INSTANCE;
    }

    public void cacheRequest(ParsingType type, RequestParams params, RequestCallBack callback) {
        cachedRequests.put(Cache.getCacheKey(type,params),new Cache(type,params,callback));
    }

    static public class Cache{
        ParsingType type;
        RequestParams params;
        RequestCallBack callback;

        public Cache(ParsingType type, RequestParams params, RequestCallBack callback) {
            this.type = type;
            this.params = params;
            this.callback = callback;
        }

        public String getCacheKey(){
            return getCacheKey(type,params);
        }

        public static String getCacheKey(ParsingType type, RequestParams params){
            return ParsingType.getUrl(type)+"?"+params.hashCode();
        }
    }
}
