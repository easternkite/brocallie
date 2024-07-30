package com.blucky8649.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blucky8649.room.model.ChatRoomEntity
import com.blucky8649.room.model.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages WHERE id = :id ORDER BY sendAt ASC")
    fun getMessagesById(id: Long): Flow<List<MessageEntity>>

    @Insert
    suspend fun insertMessage(message: MessageEntity)


    @Query(
        """
        SELECT 
            m.callieId, 
            c.name AS callieName,
            m.content_text AS lastMessageContent, 
            m.sendAt AS lastMessageSendAt 
        FROM messages m
        INNER JOIN callies c ON m.callieId = c.id
        INNER JOIN (
            SELECT callieId, MAX(sendAt) AS maxSendAt
            FROM messages
            GROUP BY callieId
        ) latest ON m.callieId = latest.callieId AND m.sendAt = latest.maxSendAt
        """
    )
    fun getChatRoomListItems(): Flow<List<ChatRoomEntity>>
}