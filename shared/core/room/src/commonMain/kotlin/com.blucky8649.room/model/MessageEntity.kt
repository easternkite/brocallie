package com.blucky8649.room.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(
    tableName = "messages",
    indices = [Index(value = ["callieId"])]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val callieId: Long,
    val authorByMe: Boolean,
    val sendAt: Instant,
    @Embedded val content: Content
)

data class Content(
    val image: String?,
    val text: String
)
