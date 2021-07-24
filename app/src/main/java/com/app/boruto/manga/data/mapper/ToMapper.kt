package com.app.boruto.manga.data.mapper

import com.app.boruto.manga.data.model.MangaResponse
import com.app.boruto.manga.domain.model.Manga

fun List<MangaResponse>.toList(): List<Manga> {
    val listManga: MutableList<Manga> = mutableListOf()
    this.forEach {
        listManga.add(
            Manga(
                title = it.title,
                numero = it.numero,
                image = it.image,
                link = it.link
            )
        )
    }
    return listManga
}