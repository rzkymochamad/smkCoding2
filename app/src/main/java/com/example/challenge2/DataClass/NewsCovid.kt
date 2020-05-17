package com.example.challenge2.DataClass


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsCovid(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
):Serializable