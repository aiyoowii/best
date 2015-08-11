package com.tianji.android.best.service.net;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cegrano on 2015/8/11.
 */
public class HttpHelper {

    static HttpUtils http = new HttpUtils();
    static Map<String,HttpHandler<File>> downloads = new HashMap<>();
    public static <T> void get(ParsingType type,RequestParams params,RequestCallBack<T> callback){
        http.send(HttpRequest.HttpMethod.GET,ParsingType.getUrl(type),params,callback);
    }
    public static <T> void post(ParsingType type,RequestParams params,RequestCallBack<T> callback){
        http.send(HttpRequest.HttpMethod.POST,ParsingType.getUrl(type),params,callback);
    }

    public static void download(String url,String savePath, final RequestCallBack<File> callBack){
        downloads.put(url,http.download(url,savePath,true,true,new RequestCallBack<File>() {
            @Override
            public void onStart() {
                callBack.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                callBack.onLoading(total, current, isUploading);
            }

            @Override
            public void onSuccess(ResponseInfo<File> fileResponseInfo) {
                callBack.onSuccess(fileResponseInfo);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callBack.onFailure(e, s);
            }
        }));
    }

    public static void cancelDownload(String url){
        downloads.get(url).cancel();
    }

}
