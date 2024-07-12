package com.blucky8649.contacts

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import brocallie.shared.feature.contacts.generated.resources.Res
import brocallie.shared.feature.contacts.generated.resources.feature_contact_title
import com.blucky8649.designsystem.BcTopAppBar
import org.jetbrains.compose.resources.stringResource

const val ROUTE_CONTACTS = "contacts"

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.contactsRoute(
    onContactClick: (String) -> Unit
) {
    composable(route = ROUTE_CONTACTS) {
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
}

fun NavHostController.navigateToContacts() {
    navigate(ROUTE_CONTACTS)
}