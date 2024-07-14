package com.blucky8649.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel = viewModel { ContactViewModel() },
    onContactClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .then(modifier)
    ) {
        items(uiState.contacts) {
            ContactItem(name = it.name) {
                onContactClick(it)
                println("click $it")
            }
        }
    }
}