package com.blucky8649.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel = viewModel { ContactViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        items(uiState.contacts) {
            ContactItem(name = it.name) {
                println("click $it")
            }
        }
    }
}