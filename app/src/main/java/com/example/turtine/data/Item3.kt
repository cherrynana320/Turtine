package com.example.turtine.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemTBL3") // SQLite 테이블 이름 지정
data class Item3(
    @PrimaryKey(autoGenerate = true) // id를 기본 키로 식별하기 위함
    val id3: Int = 0,
    @ColumnInfo(name = "routine3") // 열 이름 설정
    val itemRoutine3: String,
    @ColumnInfo(name = "min3")
    val itemMin3: Int,
    @ColumnInfo(name = "sec3")
    val itemSec3: Int
)