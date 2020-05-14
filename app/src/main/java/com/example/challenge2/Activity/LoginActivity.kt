package com.example.challenge2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challenge2.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener { validasiLogin() }
        signUp.setOnClickListener { toRegister() }
    }

    private fun toRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun validasiLogin() {
        var mSaveData = SaveData(applicationContext)
        when{
            txtEmail.text.toString().isEmpty() -> txtEmail.error = "Email Tidak Boleh Kosong"
            txtPassword.text.toString().isEmpty() -> txtPassword.error = "Password Tidak Boleh Kosong"
            mSaveData.getString("Email") != txtEmail.text.toString() ->  Toast.makeText(this, "Email Salah", Toast.LENGTH_SHORT).show()
            mSaveData.getString("Password") != txtPassword.text.toString() ->  Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show()

            else -> {
                aksiLogin()
            }
        }
    }

    private fun aksiLogin() {
        var mSaveData = SaveData(applicationContext)
        if (mSaveData.getString("Email") == txtEmail.text.toString()){
            if (mSaveData.getString("Password") == txtPassword.text.toString()){

                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
