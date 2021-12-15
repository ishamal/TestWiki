package com.example.wikitest.view.service

import com.example.wikitest.BuildConfig.*
import com.example.wikitest.view.service.helper.NetworkConnectionInterceptor
import com.example.wikitest.view.service.helper.SkipSerializedStrategy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainService {

    private val interceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    private val apiClient = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .addInterceptor(NetworkConnectionInterceptor())
        .build()

    private val gson =
        GsonBuilder().addSerializationExclusionStrategy(SkipSerializedStrategy.getStrategy())
            .create()
    private val gsonConverter = GsonConverterFactory.create(gson)

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverter)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val client: MainServiceClient = getRetrofit().create(MainServiceClient::class.java)

}