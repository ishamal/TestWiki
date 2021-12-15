package com.example.wikitest.view.repositary

import android.view.View
import com.example.wikitest.view.service.MainServiceClient
import com.example.wikitest.view.service.response.Entry
import com.example.wikitest.view.service.response.EntryDetail

class EntryRepository (private val apiInterface: MainServiceClient) : BaseRepository() {

    suspend fun getMainList(view : View?) : List<Entry>?{
        return safeApiCall(
            call = {apiInterface.getMainListAsync().await()},
            error = "Error Loading entry",
            view = view
        )
    }

    suspend fun getDetailEntry(caseId : String? ,view : View?) : List<EntryDetail>? {
        return safeApiCall(
            call = {apiInterface.getMainListDetailAsync(caseId).await()},
            error = "Error Loading data",
            view = view
        )
    }

}