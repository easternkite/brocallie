package com.blucky8649.createcallie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blucky8649.brocallie.shared.feature.createcallie.BuildKonfig
import com.blucky8649.firebase.uploadImage
import com.blucky8649.generative_ai.Gemini
import com.blucky8649.room.BrocallieDatabase
import com.blucky8649.room.model.CallieEntity
import dev.shreyaspatil.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

class CreateCallieViewModel(
    val db: BrocallieDatabase,
    val language: String = "en"
): ViewModel() {
    private val _uiState = MutableStateFlow(CreateCallieUiState())
    val uiState: StateFlow<CreateCallieUiState> = _uiState.asStateFlow()

    private val chat = Gemini.generativeModel.startChat(
        history = listOf(
            content(role = "user") { text(BuildKonfig.PROMPT_ANALYZE + "{ \"languageCode\" : \"$language }\"") }
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

    fun analyzeImage(errorMessage: String? = null, onSuccess: () -> Unit = {}) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val image = _uiState.value.image.byteArray
            if (image == null) {
                _uiState.update { it.copy(errorMessage = errorMessage, isLoading = false) }
                return@launch
            }
            val content = content {
                image(image)
            }
            val response = kotlin.runCatching { chat.sendMessage(content) }
                .onFailure {
                    println("error occurred creating callie = ${it}")
                    it.printStackTrace()
                    _uiState.update { it.copy(errorMessage = errorMessage ?: it.errorMessage, isLoading = false) }
                }
                .getOrNull()?.text

            if (response == null) {
                _uiState.update { it.copy(errorMessage = errorMessage, isLoading = false) }
                return@launch
            }

            println(response)

            runCatching { Json.decodeFromString<AnalyzedCallie>(response) }
                .onSuccess {
                    insertCallie(it)
                    onSuccess()
                }
                .onFailure { _uiState.update { it.copy(errorMessage = errorMessage, isLoading = false) } }

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private fun insertCallie(analyzed: AnalyzedCallie) = viewModelScope.launch {
        val callie = CallieEntity(
            name = analyzed.name,
            type = analyzed.type,
            personality = analyzed.personality,
            job = analyzed.job,
            tone = analyzed.tone,
            voice = analyzed.voice,
            hobby = analyzed.hobby,
            gender = analyzed.gender,
            age = analyzed.age,
            image = _uiState.value.image.url
        )
        db.callieDao().insertCallie(callie)
    }
}