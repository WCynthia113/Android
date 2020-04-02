package com.wcynthia.lifecycles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val chronometer:MyChronometer by lazy {
        findViewById<MyChronometer>(R.id.meter)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        chronometer.base = System.currentTimeMillis()//UNIX时间 1970 1.1 00：00到现在的毫秒数
//        chronometer.base = SystemClock.elapsedRealtime()//从启动到现在经过的毫秒数
        lifecycle.addObserver(chronometer)
        button.setOnClickListener {
            startActivity(Intent(this,Main2Activity::class.java))
        }
    }

}
