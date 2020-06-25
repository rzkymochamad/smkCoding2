package com.example.challenge2.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.Adapter.CovidGlobalAdapter
import com.example.challenge2.DataClass.CovidGlobalItem
import com.example.challenge2.R
import dataCovidGlobal.CovidGlobalService
import dataCovidGlobal.apiRequest
import retrofit2.Call
import retrofit2.Callback
import dataCovidGlobal.httpClient
import kotlinx.android.synthetic.*
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
//        var abc = txtSort.selectedItem.toString()
        showLoading(context!!, swipeRefreshLayout1)

        val httpClient = httpClient()
        val apiRequest = apiRequest<CovidGlobalService>(httpClient)

//        val call = apiRequest
//        val spinner = findViewById<Spinner>(R.id.txtSort)
        txtSort?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "anjay", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                Toast.makeText(context, type, Toast.LENGTH_SHORT).show()
                if (type == "Positif"){
                    val abe = apiRequest.getGlobalConfirmed()
                    abe.enqueue(object : Callback<List<CovidGlobalItem>>{
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
                                    tampilToast(context!!, ".")
                            }
                        }
                    })
                } else if(type == "Sembuh"){
                    val abe = apiRequest.getGlobalRecovered()
                    abe.enqueue(object : Callback<List<CovidGlobalItem>>{
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
                                    tampilToast(context!!, ".")
                            }
                        }
                    })
                } else if(type == "Meninggal"){
                    val abe = apiRequest.getGlobalDeaths()
                    abe.enqueue(object : Callback<List<CovidGlobalItem>>{
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
                                    tampilToast(context!!, ".")
                            }
                        }
                    })
                }
            }
        }

    }

    private fun tampilCovidGlobal(covidGlobals: List<CovidGlobalItem>){
        listCovidGlobal.layoutManager = LinearLayoutManager(context)
        listCovidGlobal.adapter =
            CovidGlobalAdapter(
                context!!,
                covidGlobals
            ) {

                val covidGlobal = it
                tampilToast(context!!, covidGlobal.combinedKey)
            }
    }

    override  fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val t = inflater.inflate(R.layout.fragment_home, container, false)

        // Inflate the layout for this fragment

        return t
    }

    override fun onViewCreated(view: View,@Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetCovidGlobal()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache ()
    }

}
