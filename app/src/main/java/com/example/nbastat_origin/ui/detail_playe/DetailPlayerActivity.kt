package com.example.nbastat_origin.ui.detail_playe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.nbastat_origin.R
import com.example.nbastat_origin.databinding.ActivityDetailPlayerBinding

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener {
            this.finish()
        }
    }

    companion object {
        private const val EXTRA_PLAYER_ID = "playerId"
        private const val EXTRA_SHOW_FAB = "showFab"

        fun start(context: Context, playerId: Int, showFab: Boolean) {
            val intent = Intent(context, DetailPlayerActivity::class.java).apply {
                putExtra(EXTRA_PLAYER_ID, playerId)
                putExtra(EXTRA_SHOW_FAB, showFab)
            }
            context.startActivity(intent)
        }
    }
}