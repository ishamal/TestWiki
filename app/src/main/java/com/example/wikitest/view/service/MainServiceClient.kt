package com.example.wikitest.view.service

import com.example.wikitest.view.service.response.Entry
import com.example.wikitest.view.service.response.EntryDetail
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface MainServiceClient {

    @Headers("Content-Type: application/json")
    @GET("scenarios")
    fun getMainListAsync() : Deferred<Response<List<Entry>>>

    @Headers("Content-Type: application/json")
    @GET("scenarios/cases/{id}")
    fun getMainListDetailAsync(@Path("id") id : String?) : Deferred<Response<List<EntryDetail>>>
}