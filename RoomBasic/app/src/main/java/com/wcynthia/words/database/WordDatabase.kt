package com.wcynthia.words.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wcynthia.words.dao.WordDao
import com.wcynthia.words.entity.Word

@Database(entities = [Word::class],version = 1,exportSchema = false)
abstract class WordDatabase :RoomDatabase(){
  companion object{
      private var INSTANCE:WordDatabase? = null
      @Synchronized fun getWordDatabase (context: Context):WordDatabase{
          if (INSTANCE == null){
              INSTANCE = Room.databaseBuilder(context.applicationContext,WordDatabase::class.java,"word_database")
//                  .fallbackToDestructiveMigration()
//                  .addMigrations(MIGRATION_1_2)
                  .build()
          }
          return INSTANCE as WordDatabase
      }
//      private val MIGRATION_1_2 = object :Migration(1,2){
//          override fun migrate(database: SupportSQLiteDatabase) {
//              database.execSQL("ALTER TABLE word ADD COLUMN foo_data INTEGER NOT NULL DEFAULT 0")
//          }
//      }
  }
    abstract fun getWordDao():WordDao


}