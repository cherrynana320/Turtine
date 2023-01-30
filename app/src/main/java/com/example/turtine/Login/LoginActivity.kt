package com.example.turtine.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.turtine.Calendar.CalendarMainActivity
import com.example.turtine.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding : ActivityLoginBinding
    var DB:DBHelper_login?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(loginBinding.root)
        DB = DBHelper_login(this)

        loginBinding.btnLogin!!.setOnClickListener {

            //editText로부터 입력된 값을 받아온다
            var id = edit_id.text.toString()
            var pw = edit_pw.text.toString()

            if (id == "" || pw == "") Toast.makeText(
                this@LoginActivity,
                "회원정보를 전부 입력해주세요",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkUserpass = DB!!.checkUserpass(id, pw)

                if (checkUserpass == true) {
                    Toast.makeText(this@LoginActivity, "로그인되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, CalendarMainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this@LoginActivity, "회원정보가 존재하지 않습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        //회원가입버튼누르면register로 이동
        loginBinding.btnRegister2.setOnClickListener {
            val loginIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(loginIntent)
        }


    }

}