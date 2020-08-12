package com.example.intnumtochinesenum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.intnumtochinesenum.TextNumUtil.translateToChinese
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt.setOnClickListener {
            tv.text = et.text.toString().toInt().translateToChinese()
        }
    }
}