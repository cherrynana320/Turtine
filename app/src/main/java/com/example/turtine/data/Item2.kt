package com.example.turtine.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemTBL2") // SQLite 테이블 이름 지정
data class Item2(
    @PrimaryKey(autoGenerate = true) // id를 기본 키로 식별하기 위함
    val id2: Int = 0,
    @ColumnInfo(name = "routine2") // 열 이름 설정
    val itemRoutine2: String,
    @ColumnInfo(name = "min2")
    val itemMin2: Int,
    @ColumnInfo(name = "sec2")
    val itemSec2: Int
)