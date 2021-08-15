package br.com.pedro.testedevandroid.model.repository

import br.com.pedro.testedevandroid.model.api.RetrofitService

class EventRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getEvents() = retrofitService.getEvents()
}