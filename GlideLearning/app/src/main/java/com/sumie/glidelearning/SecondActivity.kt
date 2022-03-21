package com.sumie.glidelearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumie.glidelearning.glide.Glide
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun t1() {
        Glide.with(this)
    }
}