package com.blucky8649.contacts.navigation

import com.blucky8649.contacts.ContactsScreen
import com.blucky8649.room.model.CallieEntity
import com.easternkite.eungabi.navigation.EunGabiController
import com.easternkite.eungabi.navigation.EunGabiGraphBuilder

const val ROUTE_CONTACTS = "contacts"

fun EunGabiGraphBuilder.contactsRoute(
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

fun EunGabiController.navigateToContacts() {
    navigate(ROUTE_CONTACTS)
}