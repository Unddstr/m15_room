package com.example.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): WordDao
}