package com.blucky8649.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blucky8649.room.BrocallieDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ContactViewModel(db: BrocallieDatabase) : ViewModel() {
    val uiState: StateFlow<ContactUiState> = db.callieDao().getCallieList()
        .map { callies -> ContactUiState(contacts = callies) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ContactUiState(isLoading = true)
        )
}
