package com.example.room.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE words ADD COLUMN word TEXT")
        db.execSQL("ALTER TABLE words ADD COLUMN repeatCount INTEGER")
    }

}