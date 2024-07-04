package com.blucky8649.contacts

data class ContactUiState(
    val isLoading: Boolean = false,
    val contacts: List<Contact> = emptyList(),
    val error: String? = null
)

