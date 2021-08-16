package br.com.pedro.testedevandroid.data.repository

import br.com.pedro.testedevandroid.data.entity.CheckIn
import br.com.pedro.testedevandroid.data.remote.EventRemoteDataSource
import javax.inject.Inject

class CheckInRepository @Inject constructor(private val remoteDataSource: EventRemoteDataSource) {
    suspend fun checkIn(body: CheckIn): Boolean {
        return try {
            remoteDataSource.checkIn(body)
            return true
        } catch (e: Exception) {
            false
        }
    }
}