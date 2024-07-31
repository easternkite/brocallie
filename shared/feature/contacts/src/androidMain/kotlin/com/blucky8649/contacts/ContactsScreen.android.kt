package com.blucky8649.contacts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val ROUTE_CONTACTS = "contacts"

fun NavGraphBuilder.contactsRoute(
    onContactClick: (String) -> Unit,
    onAddButtonClick: () -> Unit
) {
    composable(route = ROUTE_CONTACTS) {
        ContactsScreen(
            onContactClick = onContactClick,
            onAddButtonClick = onAddButtonClick
        )
    }
}

fun NavHostController.navigateToContacts() {
    navigate(ROUTE_CONTACTS)
}