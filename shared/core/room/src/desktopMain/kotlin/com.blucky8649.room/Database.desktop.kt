package com.blucky8649.room

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.koin.dsl.module
import java.io.File

val databaseModule = module {
    single { getDatabaseBuilder().build() }
}

fun getDatabaseBuilder(): RoomDatabase.Builder<BrocallieDatabase> {
    val dbFile = File("~/brocallie/db/brocallie.db")
    return Room.databaseBuilder<BrocallieDatabase>(
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(
        dropAllTables = false
    )
}
