package com.example.ringtonelearning

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var rt: Ringtone
    private var isPlaying = false

    private val url = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
    private val vibrationPattern = longArrayOf(0L, 100L, 80L, 120L)
    private val lightColor = Color.RED
    private val channelId = "ChannelId"
    private var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRingTone()
        initNotificationChannal()
        initOnclick()
    }

    private fun initOnclick(){
        bt_ring.setOnClickListener {
            if (!isPlaying) {
                playRingTone()
                bt_ring.text = getText(R.string.stop_ring)
                isPlaying = true
            } else {
                stopRingTong()
                bt_ring.text = getText(R.string.start_ring)
                isPlaying = false
            }
        }
        bt_vibrate.setOnClickListener {
            playVibrate()
        }
        bt_notification.setOnClickListener {
            useNotification()
        }
    }
    private fun initRingTone() {
        rt = RingtoneManager.getRingtone(this, url)
        rt.isLooping = true
    }

    private fun playRingTone() {
        rt.play()
    }

    private fun stopRingTong() {
        rt.stop()
    }

    private fun playVibrate() {
        val vibrator = this.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val ve =
                VibrationEffect.createWaveform(vibrationPattern, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(ve)
        } else {
            vibrator.vibrate(vibrationPattern, -1)
        }
    }
    private fun initNotificationChannal(){
        val manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel =
                NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH)
            with(mChannel) {
                //设置震动
                vibrationPattern = vibrationPattern
                enableVibration(true)
                //设置呼吸灯
                lightColor = lightColor
                enableLights(true)
                //设置声音
                setSound(url, Notification.AUDIO_ATTRIBUTES_DEFAULT)
            }
            manager.createNotificationChannel(mChannel)
        }
    }
    private fun useNotification(){//要修改震动和声音，需要卸载应用重装
        val manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("你好$num")
            .setSound(url)
            .setVibrate(vibrationPattern)
            .setLights(lightColor, 1000, 1000)
            .setGroupSummary(true)
            .setGroup("1")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
        num++
        manager.notify(num,notification)

    }
}