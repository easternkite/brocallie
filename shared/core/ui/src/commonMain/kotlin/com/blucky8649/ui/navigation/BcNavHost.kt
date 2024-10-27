package com.blucky8649.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.blucky8649.contacts.navigation.ROUTE_CONTACTS
import com.blucky8649.contacts.navigation.contactsRoute
import com.blucky8649.conversation.navigation.chatScreenRoute
import com.blucky8649.conversation.navigation.navigateToChat
import com.blucky8649.createcallie.navigation.createCallieRoute
import com.blucky8649.createcallie.navigation.navigateToCreateCallie
import com.blucky8649.profile.navigation.navigateToProfile
import com.blucky8649.profile.navigation.profileRoute
import com.blucky8649.ui.BcApp
import com.easternkite.eungabi.navigation.EunGabiController
import com.easternkite.eungabi.navigation.EunGabiNavHost
import com.easternkite.eungabi.navigation.rememberEunGabiController

@Composable
fun BcNavHost(
    modifier: Modifier = Modifier,
    controller: EunGabiController = rememberEunGabiController(),
    startDestination: String = ROUTE_CONTACTS,
) {
    BcApp {
        EunGabiNavHost(
            controller = controller,
            modifier = modifier,
            startDestination = startDestination
        ) {
            contactsRoute(
                onContactClick = controller::navigateToChat,
                onAddButtonClick = controller::navigateToCreateCallie
            )
            chatScreenRoute(
                onBackPressed = controller::navigateUp,
                onImageClick = controller::navigateToProfile
            )
            createCallieRoute(
                onBackPressed = controller::navigateUp,
                onCreateCallieClick = controller::navigateUp
            )
            profileRoute(
                onBackPressed = controller::navigateUp
            )
        }
    }
}