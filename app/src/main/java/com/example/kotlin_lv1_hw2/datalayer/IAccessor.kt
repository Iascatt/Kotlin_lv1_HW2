package com.example.kotlin_lv1_hw2.datalayer

import android.util.Log
import com.example.kotlin_lv1_hw2.objects.Data
import com.example.kotlin_lv1_hw2.objects.Gif
import com.example.kotlin_lv1_hw2.objects.Images
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface IAccessor {
    @GET("v1/gifs/trending?api_key=94MNWFdpvW4BTFldUbDPqXFhI0eQwpCS")
    suspend fun getGifs(@Query("offset") offset: Int, @Query("limit") limit: Int) : List<Gif>

    companion object {
        fun create(baseUrl: String): IAccessor {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder().apply {
                addNetworkInterceptor(loggingInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                client(client)
                addConverterFactory(DataConverterFactory())
                addConverterFactory(GsonConverterFactory.create())
                baseUrl(baseUrl)
            }.build()
            return retrofit.create(IAccessor::class.java)
        }
    }
}
