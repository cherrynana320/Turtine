package com.example.turtine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.timer

class Timer1 : AppCompatActivity() {

    private var time = 0;
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lap = 1


    lateinit var timer_sec_tv : TextView
    lateinit var timer_mil_tv : TextView
    lateinit var timer_secEdit_tv : EditText
    lateinit var setButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        timer_sec_tv = findViewById<TextView>(R.id.timer_sec_tv)
        timer_mil_tv = findViewById<TextView>(R.id.timer_mil_tv)
        timer_secEdit_tv = findViewById(R.id.timer_secEdit_tv)
        setButton = findViewById(R.id.timer_set_bt)


        setButton.setOnClickListener {
            time = timer_secEdit_tv.text.toString().toInt()*100
            start()
        }

    }


    private fun pause() {
        //timer_play_fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timerTask?.cancel()
    }


    private fun start() {
        //timer_play_fab.setImageResource(R.drawable.ic_baseline_pause_24)

        timerTask = timer(period=10){
            time--
            val sec = time / 100
            val milli = time % 100

            if( sec == 0 && milli ==0) timerTask?.cancel()
            runOnUiThread {
                timer_sec_tv.text = "$sec"
                timer_mil_tv.text = "$milli"
            }

        }
    }


    private fun reset() {
        timerTask?.cancel()
        time = 0
        isRunning = false
        //timer_play_fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        timer_sec_tv.text = "0"
        timer_mil_tv.text = "00"

        //labLayout.removeAllViews()
        lap = 1
    }

}




