package com.blucky8649.conversation

import androidx.compose.runtime.Immutable

data class ChatUiState(
    val channelName: String = "",
    val messages: List<Message> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

@Immutable
data class Message(
    val author: String = "",
    val content: String = "",
    val timestamp: String = "",
    val authorImage: String = ""
)