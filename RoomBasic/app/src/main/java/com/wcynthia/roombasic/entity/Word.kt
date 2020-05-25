package com.wcynthia.roombasic.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @ColumnInfo(name = "english_word")
    var word: String,
    @ColumnInfo(name = "chinese_meaning")
    var meaning: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

