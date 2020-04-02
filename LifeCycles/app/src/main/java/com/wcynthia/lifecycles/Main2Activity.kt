package com.wcynthia.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val chronometer = findViewById<MyChronometer>(R.id.meter)
        lifecycle.addObserver(chronometer)
    }
}
