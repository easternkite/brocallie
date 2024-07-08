package com.blucky8649.contactdetails

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_CONTACT_DETAIL = "route_contact_detail"

fun NavGraphBuilder.contanctDetailRoute(
    onBackPressed: () -> Unit,
    onSaveButtonClicked: () -> Unit
) {
    composable(route = ROUTE_CONTACT_DETAIL) {
        ContactDetailScreen(
            onBackPressed = onBackPressed,
            onSaveButtonClicked = onSaveButtonClicked
        )
    }
}

