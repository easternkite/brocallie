package com.blucky8649.contacts

import com.blucky8649.room.model.CallieEntity

data class ContactUiState(
    val isLoading: Boolean = false,
    val contacts: List<CallieEntity> = emptyList(),
    val error: String? = null
)

