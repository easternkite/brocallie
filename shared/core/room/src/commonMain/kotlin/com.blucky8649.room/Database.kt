package com.blucky8649.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.blucky8649.room.dao.CallieDao
import com.blucky8649.room.dao.MessageDao
import com.blucky8649.room.model.CallieEntity
import com.blucky8649.room.model.ChatRoomEntity
import com.blucky8649.room.model.MessageEntity
import com.blucky8649.room.util.InstantConverter

@Database(
    entities = [
        CallieEntity::class,
        ChatRoomEntity::class,
        MessageEntity::class
    ],
    version = 1
)
@TypeConverters(
    InstantConverter::class
)
abstract class BrocallieDatabase : RoomDatabase() {
    abstract fun callieDao(): CallieDao
    abstract fun message(): MessageDao
}