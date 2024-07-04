package com.blucky8649.brocallie.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.blucky8649.contacts.ROUTE_CONTACTS
import com.blucky8649.contacts.contactsScreen

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
        contactsScreen()
    }
}