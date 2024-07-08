package com.blucky8649.contacts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_CONTACTS = "contacts"

fun NavGraphBuilder.contactsRoute(
    onContactClick: (String) -> Unit
) {
    composable(route = ROUTE_CONTACTS) {
        ContactsScreen(onContactClick = onContactClick)
    }
}