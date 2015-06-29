package com.broft.ja_hw3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kevin on 2015-06-29.
 */
public class DbCreateHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TestDataBase.db";
    private static final int DATABASE_VERSION = 1;

    public DbCreateHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE broftMember (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, age INT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
