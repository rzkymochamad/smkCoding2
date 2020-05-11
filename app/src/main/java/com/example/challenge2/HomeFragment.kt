package com.example.challenge2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import dataCovidGlobal.CovidGlobalService
import dataCovidGlobal.apiRequest
import retrofit2.Call
import retrofit2.Callback
import dataCovidGlobal.httpClient
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Response
import util.dismissLoading
import util.showLoading
import util.tampilToast

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private fun callApiGetCovidGlobal(){
        showLoading(context!!, swipeRefreshLayout1)

        val httpClient = httpClient()
        val apiRequest = apiRequest<CovidGlobalService>(httpClient)

        val call = apiRequest.getGlobal()
        call.enqueue(object : Callback<List<CovidGlobalItem>>{
            override fun onFailure(call: Call<List<CovidGlobalItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout1)
            }

            override fun onResponse(
                call: Call<List<CovidGlobalItem>>,
                response: Response<List<CovidGlobalItem>>
            ) {
                dismissLoading(swipeRefreshLayout1)

                when{
                    response.isSuccessful->
                        when{
                            response.body()?.size != 0 ->
                                tampilCovidGlobal(response.body()!!)
                            else->{
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else->
                        tampilToast(context!!, "Gagal")
                }
            }
        })
    }

    private fun tampilCovidGlobal(covidGlobals: List<CovidGlobalItem>){
        listCovidGlobal.layoutManager = LinearLayoutManager(context)
        listCovidGlobal.adapter = CovidGlobalAdapter(context!!, covidGlobals){

            val covidGlobal = it
            tampilToast(context!!, covidGlobal.countryRegion)
        }
    }

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidGlobal()
    }

}
