package com.sumie.glidelearning.glide.loadData

import android.graphics.Bitmap
import android.util.Log

class EngineResource {
    companion object{
        val TAG = "EngineResource"
    }
    // 位图
    private var mBitmap: Bitmap? = null
    // key标识
    private var key: String? = null
    // 使用次数
    private var count: Int = 0
    fun getBitmap(): Bitmap? {
        return mBitmap
    }
    fun setBitmap(bitmap: Bitmap) {
        mBitmap = bitmap
    }
    fun getKey(): String? {
        return key
    }
    fun useAction() {
        if (mBitmap?.isRecycled == true) {
            Log.d(TAG, "useAction: 已经被回收了")
            return
        }
        Log.d(TAG, "useAction: 加一 count: $count")
        count ++
    }
}