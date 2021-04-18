package com.app.boruto.manga.model

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import java.lang.Exception

fun String.toSite(context: Context) {
    val builder = CustomTabsIntent.Builder()
    builder.setToolbarColor(Color.BLACK)
    val customTabsIntent = builder.build()
    try {
        customTabsIntent.launchUrl(context, Uri.parse(this))
    } catch (e: Exception) {
        Toast.makeText(
            context,
            "Ops! Erro ao abrir o site.",
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun ImageView.load(url: String, context: Context) {
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun load(init: BuildImage.() -> Unit) {
    val buildImage = BuildImage().apply(init)
    buildImage.glide()
}

class BuildImage {
    var image: ImageView? = null
    var url: String = ""
    var context: Context? = null

    fun glide() {
        Glide
            .with(context!!)
            .load(url)
            .centerCrop()
            .into(image!!)
    }
}