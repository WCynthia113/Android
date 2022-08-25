package com.sumie.glidelearning.glide.cache

import android.content.Context
import com.jakewharton.disklrucache.DiskLruCache

class DiskLruCacheImpl(context: Context) {
    var diskLruCache = DiskLruCache.open(context.cacheDir, 1, 1, (1024 * 1024 * 10).toLong())
}