package com.wcynthia.words.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.wcynthia.words.dao.WordDao
import com.wcynthia.words.database.WordDatabase
import com.wcynthia.words.entity.Word

class WordRepository(context: Context) {
    private val wordDao: WordDao
    private val allWordsLive: LiveData<MutableList<Word>>

    init {
        val wordDatabase = WordDatabase.getWordDatabase(context.applicationContext)
        wordDao = wordDatabase.getWordDao()
        allWordsLive = wordDao.getAllWords()
    }


    fun getAllWordsLive(): LiveData<MutableList<Word>>{
        return allWordsLive
    }

    fun findWordsWithPattern(pattern:String):LiveData<MutableList<Word>>{
        return wordDao.findWordsWithPattern("%$pattern%")
    }

    fun insertWords(vararg words: Word) {
        InsertAsyncTask(wordDao).execute(*words)
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

    class InsertAsyncTask(private val wordDao: WordDao) : AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg params: Word): Void? {
            wordDao.insertWords(mutableListOf(*params))
            return null
        }
    }

    class UpdateAsyncTask(private val wordDao: WordDao) : AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg params: Word): Void? {
            wordDao.updateWords(mutableListOf(*params))
            return null
        }
    }

    class DeleteAsyncTask(private val wordDao: WordDao) : AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg params: Word): Void? {
            wordDao.deleteWords(mutableListOf(*params))
            return null
        }
    }

    class DeleteAllAsyncTask(private val wordDao: WordDao) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            wordDao.deleteAllWords()
            return null
        }
    }
}