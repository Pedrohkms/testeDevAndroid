package br.com.pedro.testedevandroid.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("id") val id : Int,
    @SerializedName("eventId") val eventId : Int,
    @SerializedName("name") val name : String,
    @SerializedName("picture") val picture : String
)
