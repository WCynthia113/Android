package com.wcynthia.roombasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wcynthia.roombasic.entity.Word
import com.wcynthia.roombasic.viewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.getAllWordsLive().observe(this, Observer { list ->
            var text = ""
            for (i in 0 until list.size) {
                val word = list[i]
                text += "${word.id}:${word.word}=${word.meaning}\n"
            }
            textView.text = text
        })
        bt_insert.setOnClickListener {
            val word1 = Word("hello", "你好")
            val word2 = Word("world", "世界")
            wordViewModel.insertWords(word1,word2)
        }
        bt_clear.setOnClickListener {
            wordViewModel.deleteAllWords()
        }
        bt_update.setOnClickListener {
            val word = Word("Hi", "你好啊")
            word.id = 15
            wordViewModel.updateWords(word)
        }
        bt_delete.setOnClickListener {
            val word = Word("Hi", "你好啊")
            word.id = 14
            wordViewModel.deleteWords(word)
        }
    }
}
