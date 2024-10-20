package com.blucky8649.createcallie.navigation

import com.blucky8649.createcallie.CreateCallieScreen
import com.blucky8649.createcallie.getLanguageCode
import com.easternkite.eungabi.navigation.EunGabiController
import com.easternkite.eungabi.navigation.EunGabiGraphBuilder

const val ROUTE_CREATE_CALLIE = "route_create_callie"

fun EunGabiGraphBuilder.createCallieRoute(
    onBackPressed: () -> Unit,
    onCreateCallieClick: () -> Unit
) {
    composable(ROUTE_CREATE_CALLIE) {
        CreateCallieScreen(
            onBackButtonPressed = onBackPressed,
            onCreateClick = onCreateCallieClick,
            languageCode = getLanguageCode()
        )
    }
}

fun EunGabiController.navigateToCreateCallie() {
    navigate(ROUTE_CREATE_CALLIE)
}