package com.example.challenge2.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Activity.WebActivity
import com.example.challenge2.Adapter.NewsAdapter
import com.example.challenge2.DataClass.Article
import com.example.challenge2.DataClass.NewsCovid
import com.example.challenge2.R
import data.httpClient
import dataCovidNews.NewsService
import dataCovidNews.apiRequestNews
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.dismissLoading
import util.showLoading
import util.tampilToast


/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {

    private fun callApiNews(){
        showLoading(context!!, swipeNews)

        val httpClient = httpClient()
        val apiReq = apiRequestNews<NewsService>(httpClient)

        val call = apiReq.getNews()
        call.enqueue(object : Callback<NewsCovid> {
            override fun onFailure(call: Call<NewsCovid>, t: Throwable) {
                dismissLoading(swipeNews)
            }

            override fun onResponse(call: Call<NewsCovid>, response: Response<NewsCovid>) {
                dismissLoading(swipeNews)
//
                when{
                    response.isSuccessful->
                        when{
                            response.body()?.articles?.size != 0 ->
                                tampilNews(response.body()!!.articles)
                            else->{
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else->
                        tampilToast(context!!, "Gagal")
                }
            }
//            override fun onFailure(call: Call<List<NewsCovid>>, t: Throwable) {
//
//            }
//
//            override fun onResponse(
//                call: Call<List<NewsCovid>>,
//                response: Response<List<NewsCovid>>
//            ) {
//                dismissLoading(swipeNews)
//
//                when{
//                    response.isSuccessful->
//                        when{
//                            response.body()?.size != 0 ->
//                                tampilNews(response.body()!!)
//                            else->{
//                                tampilToast(context!!, "Berhasil")
//                            }
//                        }
//                    else->
//                        tampilToast(context!!, "Gagal")
//                }
//            }
        })
    }

    private fun tampilNews(nws: List<Article>){
        listNews.layoutManager = LinearLayoutManager(context)
        listNews.adapter = NewsAdapter(context!!, nws){
            val aq = it
            tampilToast(context!!, aq.title)

            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("abc", aq.url)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
