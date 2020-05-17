package com.example.challenge2.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Adapter.CovidProvinceAdapter
import com.example.challenge2.DataClass.Attributes
import com.example.challenge2.DataClass.CovidProvinsiItem
import com.example.challenge2.R
import dataCovidProvinsi.CovidProvinsiService
import dataCovidProvinsi.apiRequestCP
import dataCovidProvinsi.httpClient
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

    private fun callApiProvinsi(){
        showLoading(context!!, swipeProvinsi)

        val httpClient = httpClient()
        val apiNews = apiRequestCP<CovidProvinsiService>(httpClient)

        val call = apiNews.getProvinsi()
        call.enqueue(object : Callback<CovidProvinsiItem>{
            override fun onFailure(call: Call<CovidProvinsiItem>, t: Throwable) {
                dismissLoading(swipeProvinsi)
            }

            override fun onResponse(
                call: Call<CovidProvinsiItem>,
                response: Response<CovidProvinsiItem>
            ) {
                dismissLoading(swipeProvinsi)

                when{
                    response.isSuccessful->
                        when{
                            response.body()?.attributes?.size != 0 ->
                                tampilProvinsi(response.body()!!.attributes)
                            else->{
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else->
                        tampilToast(context!!,"Gagal")
                }
            }

        })
    }

    private fun tampilProvinsi(prv: List<Attributes>){
        listCovidProvinsi.layoutManager = LinearLayoutManager(context)
        listCovidProvinsi.adapter = CovidProvinceAdapter(context!!, prv){
            val bb = it
            tampilToast(context!!, bb.provinsi)

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
        return inflater.inflate(R.layout.fragment_provinsi, container, false)
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiProvinsi()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}