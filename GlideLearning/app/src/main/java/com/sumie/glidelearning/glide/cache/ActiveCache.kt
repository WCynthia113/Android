package com.sumie.glidelearning.glide.cache

import com.sumie.glidelearning.glide.loadData.EngineResource
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class ActiveCache {
    private var mapList = HashMap<String,WeakReference<EngineResource>>()

    private var queue: ReferenceQueue<EngineResource>? = null
    fun get(key: String): EngineResource? {
        val valueWeakReference = mapList[key]
        if (null != valueWeakReference) {
            return valueWeakReference.get()
        }
        return null
    }

    fun put(key: String,engineResource: EngineResource) {
        mapList[key] = WeakReference(engineResource, getQueue())
    }

    private fun getQueue(): ReferenceQueue<EngineResource> {
        if (queue == null) {
            queue = ReferenceQueue()
            thread {
                while (true) {
                    try {
                        val remove = queue!!.remove()
                        if (remove.get() != null && remove.get()?.getBitmap() != null) {
                            remove.get()!!.getBitmap()!!.recycle()
                        }
                        mapList.remove(remove.get()?.getKey())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return queue!!
    }
}