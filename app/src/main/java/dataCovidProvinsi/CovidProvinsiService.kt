package dataCovidProvinsi

import com.example.challenge2.DataClass.CovidProvinsiItem
import retrofit2.Call
import retrofit2.http.GET

interface CovidProvinsiService {
    @GET("indonesia/provinsi")
    fun getProvinsi(): Call<List<CovidProvinsiItem>>
}