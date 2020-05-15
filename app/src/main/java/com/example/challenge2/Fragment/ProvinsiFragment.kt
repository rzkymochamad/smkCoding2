package com.example.challenge2.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Adapter.CovidProvinceAdapter
import com.example.challenge2.DataClass.CovidProvinsiItem
import com.example.challenge2.R
import data.apiRequest
import data.httpClient
import dataCovidProvinsi.CovidProvinsiService
import dataCovidProvinsi.apiRequestCP
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_provinsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.dismissLoading
import util.showLoading
import util.tampilToast


/**
 * A simple [Fragment] subclass.
 */
class ProvinsiFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_provinsi, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetProvinsi()
    }

    private fun callApiGetProvinsi(){
        showLoading(context!!, swipeProvinsi)

        val httpClient = httpClient()
        val apiRequest = apiRequestCP<CovidProvinsiService>(httpClient)

        val call = apiRequest.getProvinsi()
        call.enqueue(object: Callback<List<CovidProvinsiItem>>{
            override fun onFailure(call: Call<List<CovidProvinsiItem>>, t: Throwable) {
                dismissLoading(swipeProvinsi)
            }

            override fun onResponse(
                call: Call<List<CovidProvinsiItem>>,
                response: Response<List<CovidProvinsiItem>>
            ) {
                dismissLoading(swipeProvinsi)

                when{
                    response.isSuccessful->
                        when{
                            response.body()?.size != 0->
                                tampilCovidProvince(response.body()!!)

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

    private fun tampilCovidProvince(provinsi: List<CovidProvinsiItem>){
        listCovidProvinsi.layoutManager = LinearLayoutManager(context)
        listCovidProvinsi.adapter = CovidProvinceAdapter(context!!, provinsi){
            val provinsiss = it
            tampilToast(context!!, provinsiss.attributes.provinsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}