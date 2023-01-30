package com.example.turtine

import android.app.Application
import com.example.turtine.data.ItemRoomDatabase1
import com.example.turtine.data.ItemRoomDatabase2
import com.example.turtine.data.ItemRoomDatabase3

// Application 클래스에서 데이터베이스 인스턴스를 인스턴스화
class InventoryApplication : Application(){
    
    val database1: ItemRoomDatabase1 by lazy { ItemRoomDatabase1.getDatabase(this) }
    val database2: ItemRoomDatabase2 by lazy { ItemRoomDatabase2.getDatabase(this) }
    val database3: ItemRoomDatabase3 by lazy { ItemRoomDatabase3.getDatabase(this) }
}