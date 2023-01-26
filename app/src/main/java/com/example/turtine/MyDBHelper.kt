package com.example.turtine

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

// ** 테이블을 생성하고, DB를 생성
class MyDBHelper(context: Context) : SQLiteOpenHelper(context, "routineDB", null,1) {

    // onCreate()메소드 : 테이블 생성
    override fun onCreate(db: SQLiteDatabase?) {
        // 테이블을 생성하기 위한 SQL문
         db!!.execSQL("CREATE TABLE routineTBL (rImg INTEGER , rTxt CHAR(20) PRIMARY KEY, rTime INTEGER);")
    }

//    fun setTable(db: SQLiteDatabase?){
//        db!!.execSQL("CREATE TABLE routineTBL (rImg INTEGER , rTxt CHAR(20) PRIMARY KEY, rTime INTEGER);")
//    }

    // onUpgrade메소드 : 테이블을 삭제한 후 다시 생성 ( 테이블에 버전 업데이트가 발생했을 때 )
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // SQL문 - routineTBL이 있을 때만 테이블을 삭제함
        db!!.execSQL("DROP TABLE IF EXISTS routineTBL")
        onCreate(db) // 생성할 때는 다시 SQL문 작성하는 것이 아니라 onCreate함수 호출
    }

    fun getAllData(sqlDB: SQLiteDatabase) : ArrayList<RoutineItem> {

        var routineList = ArrayList<RoutineItem>()
        var cursor : Cursor

        cursor = sqlDB.rawQuery("SELECT *From routineTBL;" , null)

        while(cursor.moveToNext()){
            var rImg = cursor.getInt(0)
            var rTxt = cursor.getString(1)
            var rTime = cursor.getInt(2)

            var ri = RoutineItem(rImg, rTxt, rTime)
            routineList.add(ri)
        }
        cursor.close()
        sqlDB.close()

        return routineList
    }
}