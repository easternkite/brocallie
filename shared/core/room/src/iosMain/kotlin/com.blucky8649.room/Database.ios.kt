package com.blucky8649.room

import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

val databaseModule = module {
    single { getDatabaseBuilder().build() }
}

fun getDatabaseBuilder(): RoomDatabase.Builder<BrocallieDatabase> {
    val dbFilePath = NSHomeDirectory().plus("/brocallie_db")
    return Room.databaseBuilder<BrocallieDatabase>(
        name = dbFilePath,
        factory = { BrocallieDatabase::class.instantiateImpl() }
    )
}