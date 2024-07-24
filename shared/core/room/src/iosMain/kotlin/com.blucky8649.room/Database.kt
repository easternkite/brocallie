package com.blucky8649.room

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<BrocallieDatabase> {
    val dbFilePath = NSHomeDirectory().plus("/brocallie_db")
    return Room.databaseBuilder<BrocallieDatabase>(
        name = dbFilePath,
        factory = { BrocallieDatabase::class.instanciateImpl() }
    )
}