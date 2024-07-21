package com.blucky8649.conversation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ROUTE_CHAT = "route_chat"
const val KEY_TITLE = "title"

fun NavGraphBuilder.chatScreenRoute(
    onBackPressed: () -> Unit,
    onImageClick: (id: String) -> Unit
) {
    composable(
        "$ROUTE_CHAT/{$KEY_TITLE}",
        arguments = listOf(navArgument(KEY_TITLE) { type = NavType.StringType })
    ) {
        ChatScreen(
            title = it.arguments?.getString(KEY_TITLE) ?: "Undefined",
            onBackPressed = onBackPressed,
            onImageClick = onImageClick
        )
    }
}

fun NavHostController.navigateToChat(title: String) {
    navigate("$ROUTE_CHAT/$title")
}