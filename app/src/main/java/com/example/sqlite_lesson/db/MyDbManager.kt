package com.example.sqlite_lesson.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(val context: Context) {
    private val myDbHelper = MyDbHelper(context)
    private lateinit var db: SQLiteDatabase

    fun open() {
        db = myDbHelper.writableDatabase
    }

    fun insert(title: String, content: String) {
        val values = ContentValues().apply {
            put(MyDb.COLUMN_NAME_TITLE, title)
            put(MyDb.COLUMN_NAME_CONTENT, content)
        }

        db.insert(MyDb.TABLE_NAME, null, values)
    }

    fun getTitlesList(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db.query(
            MyDb.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)

        with(cursor) {
            val titleIdx = cursor.getColumnIndex(MyDb.COLUMN_NAME_TITLE)

            if (titleIdx < 0) return dataList

            while (moveToNext()) {
                val dataText = cursor.getString(titleIdx)
                dataList.add(dataText)
            }

            close()
        }

        return dataList
    }

    fun close() {
        myDbHelper.close()
    }
}