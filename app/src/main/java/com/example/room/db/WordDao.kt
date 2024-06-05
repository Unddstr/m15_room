package com.example.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun getAll(): Flow<List<Word>>

    @Insert(entity = Word::class)
    suspend fun insert(word: NewWord)

    @Query("DELETE FROM words")
    suspend fun delete()

    @Query("UPDATE words SET repeatCount = repeatCount+1 WHERE word  LIKE :newWord")
    suspend fun update(newWord: String)

    @Query("SELECT * FROM words WHERE word = :wordStr")
    suspend fun getWord(wordStr: String): Word

    @Query("SELECT * FROM words ORDER BY repeatCount DESC LIMIT 5")
    fun getFirstFive(): Flow<List<Word>>
}