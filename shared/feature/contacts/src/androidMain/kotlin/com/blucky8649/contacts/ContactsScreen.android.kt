package com.blucky8649.contacts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.blucky8649.room.model.CallieEntity

const val ROUTE_CONTACTS = "contacts"

fun NavGraphBuilder.contactsRoute(
    onContactClick: (CallieEntity) -> Unit,
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