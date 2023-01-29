package com.example.turtine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class FinishActivity : AppCompatActivity() {

    lateinit var cb1: CheckBox
    lateinit var cb2: CheckBox
    lateinit var cb3: CheckBox
    lateinit var cb4: CheckBox

    lateinit var greenView: ImageView
    lateinit var orangeView: ImageView
    lateinit var redView: ImageView

    lateinit var FlagView : ImageView

    lateinit var TomainBtn : Button

    private var checkBoxes = ArrayList<CheckBox>()

    private var percent =
        (checkBoxes.count { it.isChecked }.toDouble() / checkBoxes.size.toDouble()) * 100

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
                setFlagVisibility()
            }
        }

        //메인이동버튼

        TomainBtn.setOnClickListener{
            val intentToCal = Intent(applicationContext,MainActivity::class.java)
            intentToCal.putExtra("percent", percent)
            startActivity(intentToCal)
        }
    }

    private fun setFlagVisibility() {

        if (percent >= 80) {
            greenView.isVisible = true
            orangeView.isVisible = false
            redView.isVisible = false
            FlagView=greenView

        } else if (percent >= 50) {
            greenView.isVisible = false
            orangeView.isVisible = true
            redView.isVisible = false
            FlagView=orangeView

        } else {
            greenView.isVisible = false
            orangeView.isVisible = false
            redView.isVisible = true
            FlagView=greenView
        }
    }


}

