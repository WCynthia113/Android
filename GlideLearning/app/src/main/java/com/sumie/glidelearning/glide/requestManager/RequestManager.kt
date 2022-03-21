package com.sumie.glidelearning.glide.requestManager

import androidx.fragment.app.FragmentActivity
import com.sumie.glidelearning.glide.fragment.GlideFragment

class RequestManager(private val fragmentActivity: FragmentActivity) {

    private val FRAGMENT_ACTIVITY_NAME = "Fragment_Activity_Name"

    companion object {
        val engine = Engine()
    }
    init {
        val supportFragmentManager = fragmentActivity.supportFragmentManager
        var fragment = supportFragmentManager.findFragmentByTag(FRAGMENT_ACTIVITY_NAME)
        if (fragment == null) {
            fragment = GlideFragment()
            supportFragmentManager.beginTransaction().add(fragment,FRAGMENT_ACTIVITY_NAME).commitAllowingStateLoss()
        }
    }

    fun load(url: String): Engine {
        engine.loadValueInitAction(url, fragmentActivity)

    }
}