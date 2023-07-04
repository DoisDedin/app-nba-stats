package com.example.nbastat_origin.common

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.module.AppGlideModule

class CustomGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val diskCacheSizeBytes = 10 * 1024 * 1024 // 10 MB

        builder.setDiskCache(
            DiskLruCacheFactory(
                context.cacheDir.absolutePath,
                "glide_disk_cache",
                diskCacheSizeBytes.toLong()
            )
        )
    }
}