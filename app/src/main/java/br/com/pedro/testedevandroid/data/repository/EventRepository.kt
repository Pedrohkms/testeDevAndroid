package br.com.pedro.testedevandroid.data.repository

import br.com.pedro.testedevandroid.data.local.EventDao
import br.com.pedro.testedevandroid.data.remote.EventRemoteDataSource
import br.com.pedro.testedevandroid.utils.performGetOperation
import javax.inject.Inject

class EventRepository @Inject constructor(
    private val remoteDataSource: EventRemoteDataSource,
    private val localDataSource: EventDao
) {
    fun getEvents() = performGetOperation(
        { localDataSource.getEvents() },
        { remoteDataSource.getEvents() },
        { localDataSource.insertAll(it) }
    )
    fun getEvent(id: Int) = performGetOperation(
        { localDataSource.getEvent(id) },
        { remoteDataSource.getEvent(id) },
        { localDataSource.insert(it) }
    )
}