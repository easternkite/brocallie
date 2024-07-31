package com.blucky8649.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { getDatabaseBuilder(get()).build() }
}

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<BrocallieDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("brocallie_db")
    return Room.databaseBuilder<BrocallieDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    ).fallbackToDestructiveMigration()
}