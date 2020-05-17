package com.example.challenge2.DataClass


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Source(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
):Serializable