package com.example.challenge2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Adapter.GithubUserAdapter
import com.example.challenge2.DataClass.GithubUserItem
import com.example.challenge2.R
import data.GithubService
import data.apiRequest
import data.httpClient
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_github.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.dismissLoading
import util.showLoading
import util.tampilToast

/**
 * A simple [Fragment] subclass.
 */
class GithubFragment : Fragment() {

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_github, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetGithubUser()
    }

    private fun callApiGetGithubUser(){
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<GithubService>(httpClient)

        val call = apiRequest.getUsers()
        call.enqueue(object : Callback<List<GithubUserItem>>{

            override fun onFailure(call: Call<List<GithubUserItem>>, t: Throwable){
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(call: Call<List<GithubUserItem>>, response: Response<List<GithubUserItem>>){
                dismissLoading(swipeRefreshLayout)

                when{
                    response.isSuccessful ->
                        when{
                            response.body()?.size != 0 ->
                                tampilGithubUser(response.body()!!)

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

    private fun tampilGithubUser(githubUsers: List<GithubUserItem>){
        listGithubUser.layoutManager = LinearLayoutManager(context)
        listGithubUser.adapter =
            GithubUserAdapter(
                context!!,
                githubUsers
            ) {

                val githubUser = it
                tampilToast(context!!, githubUser.login)

            }
    }

    override fun onDestroy() {
        super .onDestroy()
        this . clearFindViewByIdCache ()
    }
}
