package com.blucky8649.createcallie

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import java.util.Locale

const val ROUTE_CREATE_CALLIE = "route_create_callie"

fun NavGraphBuilder.createCallieRoute(
    onBackPressed: () -> Unit,
    onCreateCallieClick: () -> Unit
) {
    composable(ROUTE_CREATE_CALLIE) {
        val languageCode = Locale.getDefault().language
        CreateCallieScreen(
            onBackButtonPressed = onBackPressed,
            onCreateClick = onCreateCallieClick,
            languageCode = languageCode
        )
    }
}

fun NavHostController.navigateToCreateCallie() {
    navigate(ROUTE_CREATE_CALLIE)
}