package com.example.turtine

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.example.turtine.MainActivity
import  com.example.turtine.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MemoActivity : AppCompatActivity() {

    lateinit var dayText: TextView
    lateinit var btnToFinish :Button
    lateinit var btnToBack : Button


    val SP_NAME = "memo_sp_storage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_memo)

        //초기 설정 함수
        fileInit()

        //뒤로가기 버튼
        btnToBack.setOnClickListener{
            onBackPressed()
        }

        //하루마무리 이동 버튼
        btnToFinish.setOnClickListener{
            val intentToFinish = Intent(applicationContext,FinishActivity::class.java)
            intentToFinish.putExtra("day",dayText.toString())

            startActivity(intentToFinish)

        }


    }


    fun fileInit(){
        btnToFinish = findViewById(R.id.Finishwarp_btn)
        btnToBack = findViewById(R.id.Cancel_btn)
        dayText = findViewById(R.id.DayText)

        var dayTextView :String
        dayTextView =  intent.getStringExtra("Myear").toString() + "." + intent.getStringExtra("day").toString()

        dayText.setText(dayTextView)
    }


}