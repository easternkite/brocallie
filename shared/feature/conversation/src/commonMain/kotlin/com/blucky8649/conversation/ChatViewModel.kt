package com.blucky8649.conversation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    init { getConversations() }

    private fun getConversations() {
        val leeUrl = "https://img-cdn.pixlr.com/image-generator/history/65bb506dcb310754719cf81f/ede935de-1138-4f66-8ed7-44bd16efc709/medium.webp"
        val kimUrl = "https://pe-images.s3.amazonaws.com/basics/cc/image-size-resolution/resize-images-for-print/image-cropped-8x10.jpg"
        _uiState.update { it.copy(isLoading = true) }
        val messages = listOf(
            Message(AUTHOR_KIM, "I'm doing well, thanks for asking!"),
            Message(AUTHOR_LEE, "What about you?"),
            Message(AUTHOR_KIM, "I'm fine, thank you!"),
            Message(AUTHOR_LEE, "How are you?"),
            Message(AUTHOR_LEE, "Hi")
        )
        _uiState.update { it.copy(messages = messages, isLoading = false) }
    }

    fun sendMessage(message: Message) {
        val updatedMessages = _uiState.value.messages.toMutableList()
        updatedMessages.add(0, message)
        _uiState.update { it.copy(messages = updatedMessages) }
    }
}

val AUTHOR_LEE = Author(
    "1",
    "Lee",
    "https://images.unsplash.com/photo-1472491235688-bdc81a63246e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)

val AUTHOR_KIM = Author(
    "2",
    "Kim",
    "https://images.unsplash.com/photo-1507146426996-ef05306b995a?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)