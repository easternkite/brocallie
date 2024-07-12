package com.blucky8649.contacts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.ComposeUIViewController
import brocallie.shared.feature.contacts.generated.resources.Res
import brocallie.shared.feature.contacts.generated.resources.feature_contact_title
import com.blucky8649.designsystem.BcTopAppBar
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
fun ContactsViewController(onContactClick: (String) -> Unit) = ComposeUIViewController {
    Column {
        BcTopAppBar(
            title = stringResource(Res.string.feature_contact_title),
            actionIcon = Icons.Default.MoreVert,
            onActionClick = {},
            onNavigationClick = {}
        )
        ContactsScreen(onContactClick = onContactClick)
    }
}