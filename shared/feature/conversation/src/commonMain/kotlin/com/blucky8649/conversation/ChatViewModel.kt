package com.blucky8649.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blucky8649.generative_ai.Gemini
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    val chat = Gemini.generativeModel.startChat(
        history = listOf()
    )

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            val updatedMessages = _uiState.value.messages.toMutableList()
            updatedMessages.add(0, message)
            _uiState.update { it.copy(messages = updatedMessages) }

            val response = kotlin.runCatching { chat.sendMessage(message.content) }
                .getOrNull() ?: return@launch
            val newMessage = _uiState.value.messages.toMutableList()
            val messageFromModel = Message(
                AUTHOR_KIM,
                response.text.toString().trim(),
                Clock.System.now().toEpochMilliseconds().toString()
            )
            newMessage.add(0, messageFromModel)
            _uiState.update { it.copy(messages = newMessage) }
        }
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