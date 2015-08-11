package com.tianji.android.best.service.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by cegrano on 15/8/10.
 * 数据库工具类
 * <p/>
 * 所有操作，先发起网络请求，再入库，再更新UI
 */
public class DBHelper extends OrmLiteSqliteOpenHelper{
    private static final String DATABASE_NAME = "ngaDBInstance.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
