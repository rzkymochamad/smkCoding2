package dataTips

import com.example.challenge2.DataClass.TipsItem
import retrofit2.Call
import retrofit2.http.GET

interface TipsService {
    @GET("Setting")
    fun getTips(): Call<List<TipsItem>>
}