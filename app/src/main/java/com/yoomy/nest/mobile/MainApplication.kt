package com.yoomy.nest.mobile

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import dagger.hilt.android.HiltAndroidApp
import okio.Path.Companion.toOkioPath
import timber.log.Timber

@HiltAndroidApp
@SuppressLint("LogNotTimber")
class MainApplication : Application(), SingletonImageLoader.Factory {

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        Log.d(TAG, "Initializing Coil")

        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context = context, percent = 0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(
                        directory = cacheDir.resolve("nest_image_cache").toOkioPath()
                    )
                    .maxSizeBytes(size = MAX_COIL_DISK_CACHE_BYTES)
                    .build()
            }
            .crossfade(true)
            .build()
    }

    private fun initTimber() {
        Log.d(TAG, "Initializing Timber")

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        private val TAG = MainApplication::class.simpleName
        private const val MAX_COIL_DISK_CACHE_BYTES = 256 * 1024 * 1024L // 256 MB
    }
}