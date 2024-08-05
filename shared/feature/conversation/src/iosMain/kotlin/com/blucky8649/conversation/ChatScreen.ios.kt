package com.blucky8649.conversation

import androidx.compose.ui.window.ComposeUIViewController
import com.blucky8649.room.model.CallieEntity

fun ChatScreenViewController(
    callie: CallieEntity,
    onBackPressed: () -> Unit,
    onImageClick: (id: String) -> Unit
) = ComposeUIViewController {
    ChatScreen(
        callie,
        onBackPressed = onBackPressed,
        onImageClick = onImageClick
    )
}