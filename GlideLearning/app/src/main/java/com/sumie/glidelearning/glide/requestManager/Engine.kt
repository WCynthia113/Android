package com.sumie.glidelearning.glide.requestManager

import android.content.Context

class Engine {
    private var path: String? = null
    private var glideContext: Context? = null
    private var key: String? = null

    fun loadValueInitAction(path: String, requestManagerContext: Context) {
        this.path = path
        this.glideContext = requestManagerContext
    }
}