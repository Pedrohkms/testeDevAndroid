package br.com.pedro.testedevandroid.data.remote

import br.com.pedro.testedevandroid.data.entity.CheckIn
import br.com.pedro.testedevandroid.data.entity.Event
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {
    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("events/{id}")
    suspend fun getEvent(
        @Path("id") id: Int
    ) : Response<Event>

    @POST("checkin")
    suspend fun checkIn(@Body checkin: CheckIn)
}