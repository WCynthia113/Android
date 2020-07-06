package com.example.coroutineslearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun main(){

        GlobalScope.launch {
            delay(1000L)
            println("World")
        }
        println("Hello,")
        Thread.sleep(2000L)
    }
}