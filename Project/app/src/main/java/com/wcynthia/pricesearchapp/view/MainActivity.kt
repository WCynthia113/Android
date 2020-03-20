package com.wcynthia.pricesearchapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wcynthia.pricesearchapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    fun initView() {
        all_button.setOnClickListener(this)
        bosch_button.setOnClickListener(this)
        dca_button.setOnClickListener(this)
        makita_button.setOnClickListener(this)
        hitachi_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.all_button -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.bosch_button -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.dca_button -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.makita_button -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.hitachi_button -> startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}
