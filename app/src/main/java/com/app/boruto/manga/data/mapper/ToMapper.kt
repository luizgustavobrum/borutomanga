package com.app.boruto.manga.data.mapper

import com.app.boruto.manga.data.model.MangaResponse
import com.app.boruto.manga.domain.model.Manga

fun MangaResponse.toMangaModel(): Manga = Manga(
    title = this.title,
    numero = this.numero,
    image = this.image,
    link = this.link
)
