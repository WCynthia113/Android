package com.sumie.glidelearning.glide.cache

import android.os.Build
import android.util.LruCache
import com.sumie.glidelearning.glide.loadData.EngineResource

class MemoryCache(maxSize: Int) : LruCache<String, EngineResource>(maxSize) {
    private var manualRemove = false

    fun manualRemove(key: String):EngineResource? {
        manualRemove = true
        val engineResource = remove(key)
        manualRemove = false
        return engineResource
    }

    override fun sizeOf(key: String?, value: EngineResource?): Int {
        if (value == null) {
            return 0
        }
        val bitmap = value.getBitmap()
        return bitmap?.allocationByteCount ?: 0
    }
}