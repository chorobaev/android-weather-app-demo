package com.example.whetherapp.data.remote

import com.example.whetherapp.data.model.Whether
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WhetherClient {

    @GET("/data/2.5/weather")
    suspend fun getWhether(
        @Query("q") cityName: String,
        @Query("appid") appid: String = apiKey,
        @Query("units") unit: String = "metric"
    ): Whether

    companion object {
        private const val baseUtl = "https://api.openweathermap.org/"
        private const val apiKey = "0e946bb26a1704942485f34c3c154260"

        private fun getGson() = GsonBuilder().setLenient().create()

        private fun getLogger() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private fun getOkHttp() = OkHttpClient.Builder()
            .addInterceptor(getLogger())
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(baseUtl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGson()))
            .build()

        operator fun invoke() = getRetrofit().create(WhetherClient::class.java)
    }
}