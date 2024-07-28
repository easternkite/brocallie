package com.blucky8649.brocallie.android.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.blucky8649.brocallie.android.ui.BcAppState
import com.blucky8649.brocallie.android.ui.rememberBcAppState
import com.blucky8649.contacts.ROUTE_CONTACTS
import com.blucky8649.contacts.contactsRoute
import com.blucky8649.contactdetails.ROUTE_CONTACT_DETAIL
import com.blucky8649.contactdetails.contanctDetailRoute
import com.blucky8649.conversation.chatScreenRoute
import com.blucky8649.createcallie.createCallieRoute

@Composable
fun BroCallieNavHost(
    modifier: Modifier = Modifier,
    appState: BcAppState
) {
    NavHost(
        modifier = modifier,
        navController = appState.navController,
        startDestination = ROUTE_CONTACTS
    ) {
        contactsRoute {
            appState.navController.navigate(ROUTE_CONTACT_DETAIL)
        }
        contanctDetailRoute(
            onBackPressed = { appState.navController.navigateUp() },
            onSaveButtonClicked = { Log.d("DY_DEBUG", "onSaveButtonClicked") }
        )
        chatScreenRoute(
            onBackPressed = { appState.navController.navigateUp() },
            onImageClick = { /** TODO: Implement image click */ }
        )
        createCallieRoute(
            onBackPressed =  { appState.navController.navigateUp() },
            onCreateCallieClick = { /* TODO: Implement Create Callie */ }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BroCallieAppPreview() {
    BroCallieNavHost(appState = rememberBcAppState())
}