package com.blucky8649.createcallie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blucky8649.brocallie.shared.feature.createcallie.BuildKonfig
import com.blucky8649.firebase.uploadImage
import com.blucky8649.generative_ai.Gemini
import dev.shreyaspatil.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

class CreateCallieViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CreateCallieUiState())
    val uiState: StateFlow<CreateCallieUiState> = _uiState.asStateFlow()

    private val chat = Gemini.generativeModel.startChat(
        history = listOf(
            content(role = "user") { text(BuildKonfig.PROMPT_ANALYZE) }
        )
    )

    fun setImage(image: ByteArray) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val name = "brocallie_${Clock.System.now().epochSeconds}"
            image.uploadImage(name) {
                val url = it ?: ""
                val imageInfo = ImageInfo(url = url, byteArray = image)
                _uiState.update { it.copy(image = imageInfo, isLoading = false) }
            }
        }
    }

    fun analyzeImage() {
        val image = _uiState.value.image.byteArray ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val content = content {
                image(image)
            }
            val response = kotlin.runCatching { chat.sendMessage(content) }
                .onFailure { _uiState.update { it.copy(errorMessage = it.errorMessage) } }
                .getOrNull()?.text ?: return@launch

            println(response)

            runCatching { Json.decodeFromString<AnalyzedCallie>(response) }
                .onSuccess { println(it) }
                .onFailure { _uiState.update { it.copy(errorMessage = it.errorMessage) } }

            _uiState.update { it.copy(isLoading = false) }
        }
    }
}