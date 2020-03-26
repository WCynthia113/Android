package com.example.dispatchtoucheventlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnTouchListener { v, event ->
                Log.e("printout","执行了onTouch(),动作是：${event.action}")
            true
        }
        button.setOnClickListener {
            Log.e("printout","执行了onClick()")
        }
    }
}
