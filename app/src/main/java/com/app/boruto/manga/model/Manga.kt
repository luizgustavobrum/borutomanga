package com.app.boruto.manga.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Manga(
    var title: String = "",
    var numero: String = "",
    var image: String = "",
    var link: String = ""
)
