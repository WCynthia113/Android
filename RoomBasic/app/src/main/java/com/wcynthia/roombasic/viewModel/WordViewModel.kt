package com.wcynthia.roombasic.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.wcynthia.roombasic.dao.WordDao
import com.wcynthia.roombasic.database.WordDatabase
import com.wcynthia.roombasic.entity.Word
import com.wcynthia.roombasic.repository.WordRepository

class WordViewModel(application: Application) : AndroidViewModel(application){
    private val wordRepository =  WordRepository(application)

    fun getAllWordsLive(): LiveData<MutableList<Word>> {
        return wordRepository.getAllWordsLive()
    }

    fun insertWords(vararg words: Word) {
        wordRepository.insertWords(*words)
    }

    fun updateWords(vararg words: Word) {
        wordRepository.updateWords(*words)
    }

    fun deleteWords(vararg words: Word) {
        wordRepository.deleteWords(*words)
    }

    fun deleteAllWords() {
        wordRepository.deleteAllWords()
    }
}