package com.app.boruto.manga.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MangaResponse(
    var title: String = "",
    var numero: String = "",
    var image: String = "",
    var link: String = ""
)
