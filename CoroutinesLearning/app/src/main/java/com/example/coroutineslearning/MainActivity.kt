package com.example.coroutineslearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main()
    }
    private fun main(){
        GlobalScope.launch {
            Log.e("Thread one", Thread.currentThread().name)
            delay(1000L)
            println("World")
        }
        Log.e("Thread two", Thread.currentThread().name)
        println("Hello,")
        Thread.sleep(2000L)
        runBlocking { // this: CoroutineScope
            Log.e("Thread three", Thread.currentThread().name)
            launch { // 在 runBlocking 作用域中启动一个新协程
                Log.e("Thread four", Thread.currentThread().name)
                delay(1000L)
                println("World!")
            }
            println("Hello,")
        }
    }
}