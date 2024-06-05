package com.example.room

import android.app.Application
import androidx.room.Room
import com.example.room.db.AppDatabase
import com.example.room.db.MIGRATION_1_2

class App : Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}