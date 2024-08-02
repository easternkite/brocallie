package com.blucky8649.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blucky8649.brocallie.shared.feature.conversation.BuildKonfig
import com.blucky8649.generative_ai.Gemini
import com.blucky8649.room.BrocallieDatabase
import com.blucky8649.room.model.CallieEntity
import com.blucky8649.room.model.Content
import com.blucky8649.room.model.MessageEntity
import dev.shreyaspatil.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ChatViewModel(
    private val db: BrocallieDatabase,
    private val callie: CallieEntity
) : ViewModel() {

    private val initialPrompt = listOf(
        content(role = "user") { text(BuildKonfig.PROMPT_MAKE_PERSONA + Json.encodeToString(callie)) }
    )

    val uiState: StateFlow<ChatUiState> = db.message().getMessagesById(callie.id)
        .onStart { flowOf(ChatUiState(isLoading = true)) }
        .map {
            val historyPrompt = it.map { msg ->
                val role = if (msg.authorByMe) "user" else "model"
                val message = msg.content.text
                content(role) { text(message) }
            }

            chat.history.apply {
                clear()
                addAll(initialPrompt + historyPrompt.reversed())
            }

            println(chat.history.size)

            val messages = it.map { msg ->
                val author = if (msg.authorByMe) AUTHOR_ME
                else Author(callie.id.toString(), callie.name, callie.image)

                Message(
                    author = author,
                    content = msg.content.text,
                    timestamp = msg.sendAt.toEpochMilliseconds().toString()
                )
            }
            ChatUiState(messages = messages, isLoading = false)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ChatUiState(isLoading = true)
        )

    val chat = Gemini.generativeModel.startChat(
        history = initialPrompt
    )

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            val fromMe = MessageEntity(
                callieId = callie.id,
                authorByMe = true,
                sendAt = Clock.System.now(),
                content = Content(
                    image = null,
                    text = message.content
                )
            )
            db.message().insertMessage(fromMe)

            val response = kotlin.runCatching { chat.sendMessage(message.content) }
                .getOrNull() ?: return@launch
            println(response.text)

            val fromCallie = MessageEntity(
                callieId = callie.id,
                authorByMe = false,
                sendAt = Clock.System.now(),
                content = Content(
                    image = null,
                    text = response.text.toString().trim()
                )
            )
            db.message().insertMessage(fromCallie)
        }
    }
}

val AUTHOR_ME = Author(
    "1",
    "ME",
    "https://images.unsplash.com/photo-1472491235688-bdc81a63246e?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)

val AUTHOR_KIM = Author(
    "2",
    "Kim",
    "https://images.unsplash.com/photo-1507146426996-ef05306b995a?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)