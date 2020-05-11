package dataCovidGlobal

import com.example.challenge2.CovidGlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidGlobalService {
    @GET("confirmed")
    fun getGlobal(): Call<List<CovidGlobalItem>>
}