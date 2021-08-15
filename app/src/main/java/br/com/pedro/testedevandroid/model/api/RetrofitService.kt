package br.com.pedro.testedevandroid.model.api

import br.com.pedro.testedevandroid.model.Event
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(RetrofitService::class.java)
            return retrofitService as RetrofitService
        }

    }
}