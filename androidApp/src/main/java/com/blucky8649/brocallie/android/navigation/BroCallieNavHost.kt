package com.blucky8649.brocallie.android.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.blucky8649.contacts.ROUTE_CONTACTS
import com.blucky8649.contacts.contactsRoute
import com.blucky8649.contactdetails.ROUTE_CONTACT_DETAIL
import com.blucky8649.contactdetails.contanctDetailRoute

@Composable
fun BroCallieNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ROUTE_CONTACTS
    ) {
        contactsRoute {
            navController.navigate(ROUTE_CONTACT_DETAIL)
        }
        contanctDetailRoute(
            onBackPressed = { navController.navigateUp() },
            onSaveButtonClicked = { Log.d("DY_DEBUG", "onSaveButtonClicked") }
        )
    }
}