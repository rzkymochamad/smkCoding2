package com.example.challenge2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.challenge2.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        submitReg.setOnClickListener{ validasiRegister()}
        loginBack.setOnClickListener{ toLogin()}
    }

    private fun toLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun validasiRegister() {
        when{
            txtNamaReg.text.toString().isEmpty() -> txtNamaReg.error = "Nama Tidak Boleh Kosong"
            txtEmailReg.text.toString().isEmpty() -> txtEmailReg.error = "Email Tidak Boleh Kosong"
            txtPasswordReg.text.toString().isEmpty() -> txtPasswordReg.error = "Password Tidak Boleh Kosong"
            txtPasswordReg2.text.toString().isEmpty() -> txtPasswordReg2.error = "Password Tidak Boleh Kosong"
            txtPasswordReg2.text.toString() != txtPasswordReg.text.toString() -> Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_SHORT).show()

            else -> {
                aksiRegister()
            }
        }
    }

    private fun aksiRegister() {
        var mSaveData = SaveData(applicationContext)
        mSaveData.setString("Nama", txtNamaReg.text.toString())
        mSaveData.setString("Email", txtEmailReg.text.toString())
        mSaveData.setString("Password", txtPasswordReg.text.toString())

        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
