package com.gahee.myprography

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "http://ghibliapi.herokuapp.com/"

private val retrofit
        = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface FilmsApiService{

    @GET("films")
    suspend fun getFilms() : List<Film>

}

object FilmsApi{
    val retrofitService : FilmsApiService by lazy {
        retrofit.create(FilmsApiService::class.java)
    }
}