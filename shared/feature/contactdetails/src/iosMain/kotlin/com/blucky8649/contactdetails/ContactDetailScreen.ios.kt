package com.blucky8649.contactdetails

import androidx.compose.ui.window.ComposeUIViewController

fun ContactDetailViewController(
    onBackPressed: () -> Unit,
    onSaveButtonClicked: () -> Unit
) = ComposeUIViewController {
    ContactDetailScreen(
        onBackPressed = onBackPressed,
        onSaveButtonClicked = onSaveButtonClicked
    )
}