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

    fun getAllWordsLive(): LiveData<MutableList<Word>> {
        return allWordsLive
    }

    fun insertWords(vararg words: Word) {
        WordRepository.InsertAsyncTask(wordDao).execute(*words)
    }

    fun updateWords(vararg words: Word) {
        UpdateAsyncTask(wordDao).execute(*words)
    }

    fun deleteWords(vararg words: Word) {
        DeleteAsyncTask(wordDao).execute(*words)
    }

    fun deleteAllWords() {
        DeleteAllAsyncTask(wordDao).execute()
    }
}