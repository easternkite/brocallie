package com.blucky8649.contacts

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.ComposeUIViewController
import com.blucky8649.room.model.CallieEntity
import com.blucky8649.ui.BcApp

@OptIn(ExperimentalMaterial3Api::class)
fun ContactsViewController(
    onContactClick: (CallieEntity) -> Unit,
    onAddButtonClick: () -> Unit
) = ComposeUIViewController {
    BcApp { ContactsScreen(onContactClick = onContactClick, onAddButtonClick = onAddButtonClick) }

}