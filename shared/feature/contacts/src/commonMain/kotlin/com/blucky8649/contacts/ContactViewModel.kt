package com.blucky8649.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ContactUiState())
    val uiState: StateFlow<ContactUiState> = _uiState

    init {
        loadContacts()
    }

    private fun loadContacts() = viewModelScope.launch {
        _uiState.value = ContactUiState(isLoading = true)
        delay(2000L)
        val contacts = (0 ..< 100).map {
            Contact(
                id = it,
                name = "idong $it",
                phoneNumber = "010-1234-5678"
            )
        }
        _uiState.value = ContactUiState(contacts = contacts, isLoading = false)
    }

}
