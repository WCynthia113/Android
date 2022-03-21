package com.sumie.glidelearning.glide

import androidx.fragment.app.FragmentActivity
import com.sumie.glidelearning.glide.requestManager.RequestManager

class Glide {
    companion object {
        fun with(fragmentActivity: FragmentActivity): RequestManager {
            val requestManager = RequestManager(fragmentActivity)
            return requestManager
        }
    }
}