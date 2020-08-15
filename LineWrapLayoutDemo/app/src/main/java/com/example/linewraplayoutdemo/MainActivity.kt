package com.example.linewraplayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = arrayListOf<String>()
        val s = StringBuilder()
        for (i in 0 until 16) {
            s.append("我")
            list.add("${i + 1}:$s")
        }
        lineLayout1.setData(list)
        lineLayout2.setData(list)
    }
}