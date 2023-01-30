package com.example.turtine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.turtine.Calendar.CalendarMainActivity

class FinishActivity : AppCompatActivity() {

    lateinit var cb1: CheckBox
    lateinit var cb2: CheckBox
    lateinit var cb3: CheckBox
    lateinit var cb4: CheckBox

    lateinit var greenView: ImageView
    lateinit var orangeView: ImageView
    lateinit var redView: ImageView


    lateinit var TomainBtn : Button

    private var checkBoxes = ArrayList<CheckBox>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        cb1 = findViewById<CheckBox>(R.id.cb1)
        cb2 = findViewById<CheckBox>(R.id.cb2)
        cb3 = findViewById<CheckBox>(R.id.cb3)
        cb4 = findViewById<CheckBox>(R.id.cb4)

        greenView = findViewById<ImageView>(R.id.greenView)
        orangeView = findViewById<ImageView>(R.id.orangeView)
        redView = findViewById<ImageView>(R.id.redView)

        TomainBtn = findViewById<Button>(R.id.ToCalendarBtn)

        checkBoxes.addAll(listOf(cb1, cb2, cb3, cb4))
        checkBoxes.forEach {
            it.setOnCheckedChangeListener { _, _ ->

                var percent = setFlagVisibility()

                TomainBtn.setOnClickListener{
                    val intentToCal = Intent(applicationContext, MainActivity::class.java)
                    intentToCal.putExtra("percent", percent)
                    startActivity(intentToCal)

                }
            }
        }

        //메인이동버튼


    }

    fun setFlagVisibility() : Double {

        val percent =
            (checkBoxes.count { it.isChecked }.toDouble() / checkBoxes.size.toDouble()) * 100

        if (percent >= 80) {
            greenView.isVisible = true
            orangeView.isVisible = false
            redView.isVisible = false


        } else if (percent >= 50) {
            greenView.isVisible = false
            orangeView.isVisible = true
            redView.isVisible = false


        } else {
            greenView.isVisible = false
            orangeView.isVisible = false
            redView.isVisible = true

        }
        return percent

    }


}

