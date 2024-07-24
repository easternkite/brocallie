package com.blucky8649.room.model

import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

data class ChatRoomEntity(
    @PrimaryKey val callieId: Long,
    val callieName: String,
    val lastMessageContent: String,
    val lastMessageSendAt: Instant
)
