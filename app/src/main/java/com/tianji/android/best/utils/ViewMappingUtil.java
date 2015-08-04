package com.tianji.android.best.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;


public class ViewMappingUtil {
    /**
     * @param object
     * @param activity
     * @description 用于在类上映射layout
     */
    public static void mapView(Object object, Activity activity) {
        mapView(object, activity, false);
    }

    public static void mapView(Object object, Activity activity, boolean hasLayer) {
        Context context = activity;
        ViewMapping viewMapping = object.getClass().getAnnotation(ViewMapping.class);
        View rootView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                viewMapping.id(), null);
        activity.setContentView(rootView);
        mapView(object, rootView, hasLayer);
    }

    public static void mapView(Object object, View rootView) {
        startReflect(object, rootView, object.getClass());
    }

    public static void mapView(Object object, View rootView, int layer) {
        startReflect(object, rootView, object.getClass());
        Class<?> clazz = object.getClass().getSuperclass();
        for (int i = 0; i < layer - 1; i++, clazz = clazz.getSuperclass()) {
            startReflect(object, rootView, clazz);
        }
    }

    /**
     * 父类不知道子类是第几层时；调用此方法去mapping
     *
     * @param hasLayer 是否可能继承好几层
     */
    public static void mapView(Object object, View rootView, boolean hasLayer) {
        startReflect(object, rootView, object.getClass());
        if (!hasLayer)
            return;
        Class<?> clazz = object.getClass().getSuperclass();
        for (; clazz != null; clazz = clazz.getSuperclass()) {
            startReflect(object, rootView, clazz);
        }
    }

    private static void startReflect(Object object, View rootView, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        ViewMapping viewMapping;
        for (Field field : fields) {
            viewMapping = field.getAnnotation(ViewMapping.class);
            int id = 0;
            if (viewMapping != null) {
                try {
                    id = viewMapping.id();
                    field.setAccessible(true);
                    field.set(object, rootView.findViewById(id));
                } catch (Exception e) {
                    throw new RuntimeException(id + " map error!");
                }
            }
        }
    }

    public static View mapView(Object object, Context context) {
        return mapView(object, context, null, false);
    }

    public static View mapView(Object object, Context context, ViewGroup parent, boolean isAttach) {
        ViewMapping viewMapping = object.getClass().getAnnotation(ViewMapping.class);
        View rootView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                viewMapping.id(), parent, isAttach);
        mapView(object, rootView);
        return rootView;
    }

}
