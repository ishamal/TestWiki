package com.example.wikitest.view.service.response

class MainResponse {
}

data class Entry(
    var caseid : Int?,
    var text : String?
)

data class EntryDetail(
    var id : Int?,
    var text : String?,
    var image : String?,
    var answers : List<Entry>? = null
)