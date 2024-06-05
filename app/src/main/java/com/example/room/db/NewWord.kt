package com.example.room.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class NewWord(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "repeatCount")
    val repeatCount: Int
)