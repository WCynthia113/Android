package com.wcynthia.lifecycles

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider

class MyChronometer(context: Context, attrs: AttributeSet) : Chronometer(context, attrs),
    LifecycleObserver {

    private val elapsedTimeVM by lazy {
        ViewModelProvider(context as AppCompatActivity).get(ElapsedTimeVM::class.java)
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun pauseMeter() {
        elapsedTimeVM.elapsedTime = SystemClock.elapsedRealtime() - base
        stop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun resumeMeter() {
        base = SystemClock.elapsedRealtime() - elapsedTimeVM.elapsedTime
        start()
    }
}