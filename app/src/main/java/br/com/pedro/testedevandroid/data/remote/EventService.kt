package br.com.pedro.testedevandroid.data.remote

import br.com.pedro.testedevandroid.data.entity.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventService {
    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("events/{id}")
    suspend fun getEvent(
        @Path("id") id: Int
    ) : Response<Event>
}