package data

import com.example.challenge2.DataClass.GithubUserItem
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("users")
    fun getUsers(): Call<List<GithubUserItem>>
}