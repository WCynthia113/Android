package com.sumie.glidelearning.glide.requestManager

import android.content.Context
import android.widget.ImageView
import com.jakewharton.disklrucache.DiskLruCache
import com.sumie.glidelearning.glide.cache.ActiveCache
import com.sumie.glidelearning.glide.cache.MemoryCache
import com.sumie.glidelearning.glide.loadData.EngineResource

class Engine {
    private var path: String? = null
    private var glideContext: Context? = null
    private var key: String? = null

    private var imageView: ImageView? = null

    private var activeCache: ActiveCache = ActiveCache()

    private var memoryCache: MemoryCache = MemoryCache(1024*1024*60)

    private var diskCache:
    fun loadValueInitAction(path: String, requestManagerContext: Context) {
        this.path = path
        this.glideContext = requestManagerContext
        key = Key(path).getKey()
    }
    fun into(imageView: ImageView) {
        this.imageView = imageView
        // 开启加载任务
        // 活动缓存
    }

    private fun cacheAction(): EngineResource {
        if (key != null) {
            val key = this.key!!
            var engineResource = activeCache.get(key)
            if (engineResource != null) {
                // 计数器 + 1
                engineResource.useAction()
                return engineResource
            }

            engineResource = memoryCache.get(key)
            if (null != engineResource) {
                memoryCache.manualRemove(key)
                activeCache.put(key,engineResource)
                engineResource.useAction()
                return  engineResource
            }
        }
    }
}