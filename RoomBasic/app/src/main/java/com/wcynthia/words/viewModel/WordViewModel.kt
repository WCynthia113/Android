package com.wcynthia.words.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.wcynthia.words.entity.Word
import com.wcynthia.words.repository.WordRepository

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