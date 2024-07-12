package com.blucky8649.contactdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.ComposeUIViewController
import brocallie.shared.feature.contactdetails.generated.resources.Res
import brocallie.shared.feature.contactdetails.generated.resources.feature_contact_detail_title
import com.blucky8649.designsystem.BcTopAppBar
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
fun ContactDetailViewController(
    onBackPressed: () -> Unit,
    onSaveButtonClicked: () -> Unit
) = ComposeUIViewController {
    Column {
        BcTopAppBar(
            title = stringResource(Res.string.feature_contact_detail_title),
            navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
            navigationIconContentDescription = stringResource(Res.string.feature_contact_detail_title),
            actionIcon = Icons.Default.MoreVert,
            actionIconContentDescription = "",
            onActionClick = {},
            onNavigationClick = onBackPressed
        )
        ContactDetailScreen(
            onBackPressed = onBackPressed,
            onSaveButtonClicked = onSaveButtonClicked
        )
    }
    }