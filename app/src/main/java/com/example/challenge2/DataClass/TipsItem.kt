package com.example.challenge2.DataClass


import com.google.gson.annotations.SerializedName

data class TipsItem(
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("gambar")
    val gambar: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("judul")
    val judul: String
)