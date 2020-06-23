package com.example.challenge2.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challenge2.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progress.visibility = View.GONE
        btn_login.setOnClickListener { validasiLogin() }
        signUp.setOnClickListener { toRegister() }
        btn_gugel.setOnClickListener(this)
    }

    private fun toRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

//    private fun toGoogle() {
//        auth = FirebaseAuth.getInstance()
//
//        if (auth!!.currentUser == null) {
//        } else {
//            intent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN = kode permintaan yang diberikan ke startActivityForResult
        if (requestCode == RC_SIGN_IN) {
            //Jika Berhasil masuk
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else { //Jika gagal login
                progress.visibility = View.GONE
                Toast.makeText(this, "Login Dibatalkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View?) {
        // Statement program untuk login/masuk
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)
            progress.visibility = View.VISIBLE

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
