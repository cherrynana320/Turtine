package com.example.turtine

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SampleActivity : AppCompatActivity() {
    lateinit var myHelper : MyDBHelper
    lateinit var sqlDB : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        // var etImg = findViewById<EditText>(R.id.sample_routine_img_et)
//        var etTxt = findViewById<EditText>(R.id.sample_routine_txt_et)
//        var etTime = findViewById<EditText>(R.id.sample_routine_time_et)

        var etTxt = "plz"
        var etTime = 5

        var btnReg = findViewById<Button>(R.id.sample_reg_btn)

        myHelper = MyDBHelper(this)

        // 데이터 베이스에 등록
        btnReg.setOnClickListener{
            sqlDB = myHelper.writableDatabase

//            sqlDB.execSQL("INSERT INTO routineTBL VALUES ('"
//                    +"R.drawable.ic_baseline_timer_24"+ "',"
//                    +etTxt.text.toString()+ "',"
//                    +etTime.text.toString()+ "); ")

            sqlDB.execSQL("INSERT INTO routineTBL VALUES ('"
                    +"R.drawable.ic_baseline_timer_24"+ "',"
                    +etTxt+ "',"
                    +etTime+ "); ")

            sqlDB.close()
            Toast.makeText(applicationContext, "등록됨", Toast.LENGTH_SHORT).show()


            var intent = Intent(this, TodayRoutineActivity::class.java)
            startActivity(intent)

        }




    }

}