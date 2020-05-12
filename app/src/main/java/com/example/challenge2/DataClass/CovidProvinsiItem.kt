package com.example.challenge2.DataClass


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CovidProvinsiItem(
    @SerializedName("attributes")
    val attributes: Attributes
):Serializable