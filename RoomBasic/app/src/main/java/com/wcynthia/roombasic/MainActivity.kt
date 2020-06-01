package com.wcynthia.roombasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcynthia.roombasic.entity.Word
import com.wcynthia.roombasic.viewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adapterCard = MyAdapter(true)
    private val adapterNormal = MyAdapter(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapterNormal
        val wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.getAllWordsLive().observe(this, Observer { list ->
            adapterCard.setData(list)
            adapterNormal.setData(list)
            adapterCard.notifyDataSetChanged()
            adapterNormal.notifyDataSetChanged()
        })
        bt_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                rv.adapter = adapterCard
            }else{
                rv.adapter = adapterNormal
            }
        }
        bt_insert.setOnClickListener {
            val english = arrayOf(
                "hello",
                "world",
                "Android",
                "Google",
                "Studio",
                "Project",
                "Database",
                "Recycler",
                "View",
                "String",
                "Value",
                "Integer"
            )
            val chinese = arrayOf(
                "你好",
                "世界",
                "安卓系统",
                "谷歌公司",
                "工作室",
                "项目",
                "数据库",
                "回收站",
                "视图",
                "字符串",
                "价值",
                "整数类型"
            )
            for (i in english.indices) {
                wordViewModel.insertWords(Word(english[i], chinese[i]))
            }
        }
        bt_clear.setOnClickListener {
            wordViewModel.deleteAllWords()
        }
    }
}