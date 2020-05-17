package dataCovidGlobal

import com.example.challenge2.DataClass.CovidGlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidGlobalService {
    @GET("confirmed")
    fun getGlobalConfirmed(): Call<List<CovidGlobalItem>>
    @GET("recovered")
    fun getGlobalRecovered(): Call<List<CovidGlobalItem>>
    @GET("deaths")
    fun getGlobalDeaths(): Call<List<CovidGlobalItem>>


}