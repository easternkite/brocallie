package com.blucky8649.contacts

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_CONTACTS = "contacts"

fun NavGraphBuilder.contactsScreen() {
    composable(route = ROUTE_CONTACTS) {
        ContactsScreen()
    }
}