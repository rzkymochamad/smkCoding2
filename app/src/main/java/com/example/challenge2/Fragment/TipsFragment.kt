package com.example.challenge2.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Activity.FriendActivity
import com.example.challenge2.Adapter.TipsAdapter
import com.example.challenge2.DataClass.TipsItem
import com.example.challenge2.R
import dataTips.TipsService
import dataTips.apiTips
import dataTips.httpClient
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_tips.*
import kotlinx.android.synthetic.main.fragment_tips.swipeTips
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.dismissLoading
import util.showLoading
import util.tampilToast


/**
 * A simple [Fragment] subclass.
 */
class TipsFragment : Fragment() {

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetGithubUser()
        tambah.setOnClickListener {
            val intent = Intent (context, FriendActivity::class.java)
            startActivity(intent)
        }
    }

    private fun callApiGetGithubUser(){
        showLoading(context!!, swipeTips)

        val httpClient = httpClient()
        val apiRequest = apiTips<TipsService>(httpClient)

        val call = apiRequest.getTips()
        call.enqueue(object : Callback<List<TipsItem>> {

            override fun onFailure(call: Call<List<TipsItem>>, t: Throwable){
                dismissLoading(swipeTips)
            }

            override fun onResponse(call: Call<List<TipsItem>>, response: Response<List<TipsItem>>){
                dismissLoading(swipeTips)

                when{
                    response.isSuccessful ->
                        when{
                            response.body()?.size != 0 ->
                                tampilTips(response.body()!!)

                            else->{
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else->{
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun tampilTips(ib: List<TipsItem>){
        listTips.layoutManager = LinearLayoutManager(context)
        listTips.adapter =
            TipsAdapter(
                context!!,
                ib
            ) {

                val asc = it
                tampilToast(context!!, asc.judul)

            }
    }

    override fun onDestroy() {
        super .onDestroy()
        this . clearFindViewByIdCache ()
    }

}
