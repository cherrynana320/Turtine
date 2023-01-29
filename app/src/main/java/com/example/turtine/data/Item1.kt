package com.example.turtine.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemTBL1") // SQLite 테이블 이름 지정
data class Item1(
    @PrimaryKey(autoGenerate = true) // id를 기본 키로 식별하기 위함
    val id1: Int = 0,
    @ColumnInfo(name = "routine1") // 열 이름 설정
    val itemRoutine1: String,
    @ColumnInfo(name = "min2")
    val itemMin1: Int,
    @ColumnInfo(name = "sec3")
    val itemSec1: Int
)

