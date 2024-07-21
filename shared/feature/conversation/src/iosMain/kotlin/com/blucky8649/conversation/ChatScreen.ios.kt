package com.blucky8649.conversation

import androidx.compose.ui.window.ComposeUIViewController

fun ChatScreenViewController(
    title: String,
    onBackPressed: () -> Unit,
    onImageClick: (id: String) -> Unit
) = ComposeUIViewController {
    ChatScreen(
        title = title,
        onBackPressed = onBackPressed,
        onImageClick = onImageClick
    )
}