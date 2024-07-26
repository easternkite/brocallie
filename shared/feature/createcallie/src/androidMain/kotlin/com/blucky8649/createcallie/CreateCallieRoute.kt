package com.blucky8649.createcallie

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val ROUTE_CREATE_CALLIE = "route_create_callie"

fun NavGraphBuilder.createCallieRoute(
    onBackPressed: () -> Unit,
    onCreateCallieClick: () -> Unit
) {
    composable(ROUTE_CREATE_CALLIE) {
        CreateCallieScreen(
            onBackButtonPressed = onBackPressed,
            onCreateClick = onCreateCallieClick
        )
    }
}

fun NavHostController.navigateToCreateCallie() {
    navigate(ROUTE_CREATE_CALLIE)
}