package com.example.challenge2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Adapter.MyFriendAdapter
import com.example.challenge2.DataClass.MyFriend
import com.example.challenge2.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_my_friend.*

/**
 * A simple [Fragment] subclass.
 */
class MyFriendFragment : Fragment() {

    lateinit var listTeman:ArrayList<MyFriend>
    private fun simulasiDataTeman(){
        listTeman = ArrayList()
        listTeman.add(
            MyFriend(
                "Rizky",
                "Laki-laki",
                "rizky@smkcoding.id",
                "081666777888",
                "Malang"
            )
        )
        listTeman.add(
            MyFriend(
                "Can",
                "Perempuan",
                "can@smkcoding.id",
                "081222333444",
                "Lawang"
            )
        )
    }

    private fun tampilTeman(){
        rv_listMyFriends.layoutManager = LinearLayoutManager(activity)
        rv_listMyFriends.adapter =
            MyFriendAdapter(activity!!, listTeman)
    }

    private fun initView(){
        simulasiDataTeman()
        tampilTeman()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_friend, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
