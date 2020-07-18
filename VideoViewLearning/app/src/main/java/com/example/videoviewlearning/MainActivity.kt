package com.example.videoviewlearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val videoPath = "android.resource://$packageName/${R.raw.media}"
        videoView.setVideoPath(videoPath)
        videoView.setMediaController(MediaController(this))
        videoView.start()
        runBlocking {

        }
        lifecycleScope.launch {
            Log.e("thread","")
        }
    }

}