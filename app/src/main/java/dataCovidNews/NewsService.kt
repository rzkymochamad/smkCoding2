package dataCovidNews

import com.example.challenge2.DataClass.NewsCovid
import retrofit2.Call
import retrofit2.http.GET

interface NewsService {
    @GET("top-headlines?country=id&category=health&apiKey=5ae56fb3d313455e88174080a995aac8")
    fun getNews(): Call<NewsCovid>
}