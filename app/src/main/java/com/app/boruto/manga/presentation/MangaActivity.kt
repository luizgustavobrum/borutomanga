package com.app.boruto.manga.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.app.boruto.manga.databinding.ActivityMangaBinding
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MangaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMangaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMangaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragment.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    companion object {
        fun toMangaActivity(activity: Activity) {
            activity.run {
                startActivity(Intent(activity, MangaActivity::class.java))
                Animatoo.animateFade(this)
                finish()
            }

        }
    }
}