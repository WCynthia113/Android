package com.wcynthia.roombasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wcynthia.roombasic.entity.Word
import com.wcynthia.roombasic.viewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private val wordDatabase by lazy {
//        WordDatabase.getWordDatabase(this)
////        Room.databaseBuilder(this, WordDatabase::class.java, "word_database")
////            .allowMainThreadQueries().build()
//    }
//    private val wordDao by lazy {
//        wordDatabase.getWordDao()
//    }
//    private val allWordsLive by lazy {
//        wordDao.getAllWords()
//    }

//    private val wordViewModel by lazy {
//        ViewModelProvider(this).get(WordViewModel::class.java)
//    }
    //
//    private lateinit var wordDao:WordDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val wordDatabase = Room.databaseBuilder(this,WordDatabase::class.java,"word_database").allowMainThreadQueries().build()
//        wordDao = wordDatabase.getWordDao()
//        updateView()
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
//            wordDao.insertWords(mutableListOf(word1, word2))
//            updateView()
        }
        bt_clear.setOnClickListener {
            wordViewModel.deleteAllWords()
//            wordDao.deleteAllWords()
//            DeleteAllAsyncTask(wordDao).execute()
//            updateView()
        }
        bt_update.setOnClickListener {
            val word = Word("Hi", "你好啊")
            word.id = 15
            wordViewModel.updateWords(word)
//            UpdateAsyncTask(wordDao).execute(word)
//            wordDao.updateWords(mutableListOf(word))
//            updateView()
        }
        bt_delete.setOnClickListener {
            val word = Word("Hi", "你好啊")
            word.id = 14
            wordViewModel.deleteWords(word)
//            DeleteAsyncTask(wordDao).execute(word)
//            wordDao.deleteWords(mutableListOf(word))
//            updateView()
        }

    }

//    private fun updateView(){
//        val list = wordDao.getAllWords()
//        var text = ""
//        for (i in 0 until list.size)
//        {
//            val word = list[i]
//            text += "${word.id}:${word.word}=${word.meaning}\n"
//        }
//        textView.text = text
//    }
}
