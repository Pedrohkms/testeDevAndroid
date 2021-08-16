package br.com.pedro.testedevandroid.data.remote

import javax.inject.Inject

class EventRemoteDataSource @Inject constructor(
    private val eventService: EventService
) : BaseDataSource() {
    suspend fun getEvents() = getResult { eventService.getEvents() }
    suspend fun getEvent(id: Int) = getResult { eventService.getEvent(id) }
}