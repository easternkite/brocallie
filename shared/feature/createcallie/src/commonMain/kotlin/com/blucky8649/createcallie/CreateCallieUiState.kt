package com.blucky8649.createcallie

import kotlinx.serialization.Serializable

data class CreateCallieUiState(
    val isLoading: Boolean = false,
    val analyzedCallie: AnalyzedCallie = AnalyzedCallie(),
    val image: ImageInfo = ImageInfo(),
    val errorMessage: String? = null
)

data class ImageInfo(
    val url: String = "",
    val byteArray: ByteArray? = null
)

@Serializable
data class AnalyzedCallie(
    val name: String = "",
    val personality: String = "",
    val job: String = "",
    val tone: String = "",
    val voice: String = "",
    val hobby: String = "",
    val gender: String = "",
    val age: Int = 0
)