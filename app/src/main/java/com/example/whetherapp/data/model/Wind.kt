package com.example.whetherapp.data.model


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed")
    val speed: Int,
    @SerializedName("deg")
    val deg: Int
)