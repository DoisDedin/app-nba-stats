package com.example.nbastat_origin.ui.detail_playe

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.nbastat_origin.databinding.ActivityDetailPlayerBinding
import com.example.nbastat_origin.ui.list_players.home.vo.PlayerVO

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPlayerBinding


    private var playerVO: PlayerVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener {
            this.finish()
        }

        setupDataInLayout(intent.getParcelableExtra(EXTRA_PLAYER_VO))
    }



    private fun setupDataInLayout(playerVo: PlayerVO?) {
        playerVo?.let {playerSafe ->
            Glide.with(this)
                .load(playerSafe.photoUrl)
                .apply(RequestOptions.circleCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Opcional: cache da imagem
                .into(binding.imageViewPlayerUrl)

            binding.colapsingToolbarTitle.title = playerSafe.firstName + playerSafe.lastName
            binding.tvPlayerName.text = playerSafe.firstName + playerSafe.lastName
        }

    }
    companion object {
        private const val EXTRA_PLAYER_VO= "playerVo"
        private const val EXTRA_SHOW_FAB = "showFab"

        fun start(context: Context, playerVo: PlayerVO, showFab: Boolean) {
            val intent = Intent(context, DetailPlayerActivity::class.java).apply {
                putExtra(EXTRA_PLAYER_VO, playerVo)
                putExtra(EXTRA_SHOW_FAB, showFab)
            }
            context.startActivity(intent)
        }
    }
}