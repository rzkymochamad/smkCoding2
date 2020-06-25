package com.example.challenge2.Activity

import FriendsModel
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge2.Fragment.MyFriendFragment
import com.example.challenge2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_friend.*
import kotlinx.android.synthetic.main.fragment_my_friends.*


class FriendActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)

        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()

        simpan.setOnClickListener{
            prosesSave()
        }
    }

    private fun prosesSave() {
        val getNama: String = txt_nama.text.toString()
        val getEmail: String = txt_email.text.toString()
        val getTelp: String = txt_telp.text.toString()
        val getAlamat: String = txt_alamat.text.toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()

        if(getNama.isEmpty() && getEmail.isEmpty() && getTelp.isEmpty() && getAlamat.isEmpty()){
            Toast.makeText(this@FriendActivity, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        } else{
            val teman = FriendsModel(getNama, getEmail, getTelp, getAlamat, getUserID)
            ref.child(getUserID).child("Teman").push().setValue(teman).addOnCanceledListener {
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent (this, MyFriendFragment::class.java)
            startActivity(intent)
            finish()
        }
    }
}