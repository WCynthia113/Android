package com.wcynthia.roombasic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wcynthia.roombasic.dao.WordDao
import com.wcynthia.roombasic.entity.Word

@Database(entities = [Word::class],version = 1,exportSchema = false)
abstract class WordDatabase :RoomDatabase(){
  companion object{
      private var INSTANCE:WordDatabase? = null
      @Synchronized fun getWordDatabase (context: Context):WordDatabase{
          if (INSTANCE == null){
              INSTANCE = Room.databaseBuilder(context.applicationContext,WordDatabase::class.java,"word_database").build()
          }
          return INSTANCE as WordDatabase
      }

  }
    abstract fun getWordDao():WordDao

}