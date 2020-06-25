package com.example.challenge2.Fragment

import FriendsModel
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Activity.FriendActivity
import com.example.challenge2.Adapter.MyFriendAdapter
import com.example.challenge2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friends.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.dismissLoading
import util.showLoading
import util.tampilToast


/**
 * A simple [Fragment] subclass.
 */
class MyFriendFragment : Fragment(){

    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var dataTeman: ArrayList<FriendsModel>
//    lateinit var listTeman : ArrayList<MyFriend>

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_friends, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        plus.setOnClickListener {
            val intent = Intent (context, FriendActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getData() {
        //Mendapatkan Referensi Database
        Toast.makeText(getContext(), "Mohon Tunggu...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Teman").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Database Error", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                dataTeman = java.util.ArrayList<FriendsModel>()
                for (snapshot in snapshot.children){
                    val teman = snapshot.getValue(FriendsModel::class.java)
                    teman?.key = (snapshot.key!!)
                    dataTeman.add(teman!!)
                }

                rv_listFriend.layoutManager = LinearLayoutManager(context)
                rv_listFriend.adapter = MyFriendAdapter(context!!, dataTeman)

                Toast.makeText(context, "Data Berhasil Dimuat", Toast.LENGTH_LONG).show()
            }
        })

    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache ()
    }

}
