package com.app.boruto.manga.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.boruto.manga.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        toMangaActivity()
    }

    private fun toMangaActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            MangaActivity.toMangaActivity(this)
        }, 1000)
    }
}