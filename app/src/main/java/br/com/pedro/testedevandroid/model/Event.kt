package br.com.pedro.testedevandroid.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("people") val people : List<People>,
    @SerializedName("date") val date : Int,
    @SerializedName("description") val description : String,
    @SerializedName("image") val image : String,
    @SerializedName("longitude") val longitude : Double,
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("price") val price : Double,
    @SerializedName("title") val title : String,
    @SerializedName("id") val id : Int
)