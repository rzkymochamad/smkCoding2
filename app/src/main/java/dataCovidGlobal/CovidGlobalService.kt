package dataCovidGlobal

import com.example.challenge2.DataClass.CovidGlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidGlobalService {
    @GET("confirmed")
    fun getGlobalCases(): Call<List<CovidGlobalItem>>


}