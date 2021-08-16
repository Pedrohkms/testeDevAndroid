package br.com.pedro.testedevandroid.di

import android.content.Context
import br.com.pedro.testedevandroid.data.entity.CheckIn
import br.com.pedro.testedevandroid.data.local.AppDatabase
import br.com.pedro.testedevandroid.data.local.EventDao
import br.com.pedro.testedevandroid.data.remote.EventRemoteDataSource
import br.com.pedro.testedevandroid.data.remote.EventService
import br.com.pedro.testedevandroid.data.repository.CheckInRepository
import br.com.pedro.testedevandroid.data.repository.EventRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideEventService(retrofit: Retrofit): EventService =
        retrofit.create(EventService::class.java)

    @Singleton
    @Provides
    fun provideEventRemoteDataSource(eventService: EventService) =
        EventRemoteDataSource(eventService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideEventDao(db: AppDatabase) = db.eventDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: EventRemoteDataSource,
        localDataSource: EventDao
    ) =
        EventRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideCheckinRepository(remoteDataSource: EventRemoteDataSource) =
        CheckInRepository(remoteDataSource)

}