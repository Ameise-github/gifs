package com.example.gifs.data.network

import com.example.gifs.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

@OptIn(ExperimentalSerializationApi::class)
class ClientGiphy {
    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }

    private val keyInterceptor = Interceptor { chain ->
        val url: HttpUrl =
            chain.request().url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(keyInterceptor)
        addInterceptor(loggingInterceptor)
    }.build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_GIPHY_BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    val api = retrofit.create(ApiGiphy::class.java)


}