package com.example.sqlite_lesson.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context)
    : SQLiteOpenHelper(context, MyDb.DATABASE_NAME, null, MyDb.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyDb.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyDb.SQL_DELETE_TABLE)
        onCreate(db)
    }
}