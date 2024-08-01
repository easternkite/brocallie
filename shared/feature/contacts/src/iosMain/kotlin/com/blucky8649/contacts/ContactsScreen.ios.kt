package com.blucky8649.contacts

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.ComposeUIViewController

@OptIn(ExperimentalMaterial3Api::class)
fun ContactsViewController(onContactClick: (String) -> Unit) = ComposeUIViewController {
    ContactsScreen(onContactClick = { onContactClick("Callie") })
}