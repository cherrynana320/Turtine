package com.example.turtine.Login

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.turtine.R
import com.example.turtine.databinding.ActivityLoginBinding
import com.example.turtine.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var registerBinding: ActivityRegisterBinding
    var DB:DBHelper_login?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(registerBinding.root)
        DB = DBHelper_login(this)

        registerBinding.btnRegister2.setOnClickListener {
            val name = edit_name.text.toString()
            val id = edit_id.text.toString()
            val pw = edit_pw.text.toString()
            val repw = edit_pw_re.text.toString()

            if (name == "" || id == "" || pw == "" || repw == "") Toast.makeText(
                this@RegisterActivity,
                "회원정보를 전부 입력해주세요",
                Toast.LENGTH_SHORT
            ).show() else
                if (pw == repw) {
                    val checkUsername = DB!!.checkUsername(id)
                    if (checkUsername == false) {
                        val insert = DB!!.insertData(id, pw)
                        if (insert == true) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "가입되었습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext,LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(
                                this@RegisterActivity,
                                "비밀번호가 일치하지 않습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }else{
                        Toast.makeText(
                            this@RegisterActivity,
                            "이미 가입된 회원입니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    Toast.makeText(this@RegisterActivity,"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                        .show()
                }

        }
    }
}
