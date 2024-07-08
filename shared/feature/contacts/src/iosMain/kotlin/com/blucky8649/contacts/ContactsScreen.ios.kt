package com.blucky8649.contacts

import androidx.compose.ui.window.ComposeUIViewController

fun ContactsViewController(onContactClick: (String) -> Unit) = ComposeUIViewController {
    ContactsScreen(onContactClick = onContactClick)
}