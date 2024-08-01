package com.blucky8649.conversation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.blucky8649.room.model.CallieEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val ROUTE_CHAT = "route_chat"
const val KEY_CALLIE = "key_callie"
const val KEY_IMAGE = "key_image"

fun NavGraphBuilder.chatScreenRoute(
    onBackPressed: () -> Unit,
    onImageClick: (id: String) -> Unit
) {
    composable(
        "$ROUTE_CHAT?callie={$KEY_CALLIE}&image={$KEY_IMAGE}",
        arguments = listOf(navArgument(KEY_CALLIE) { type = NavType.StringType })
    ) {
        val callieJson = it.arguments?.getString(KEY_CALLIE)
        val image = it.arguments?.getString(KEY_IMAGE)

        println("callieJson = $callieJson")
        val callie = callieJson
            ?.let { Json.decodeFromString<CallieEntity>(it) }
            ?.copy(image = image ?: "")
            ?: CallieEntity()

        ChatScreen(
            callie = callie,
            onBackPressed = onBackPressed,
            onImageClick = onImageClick
        )
    }
}


fun NavHostController.navigateToChat(callie: CallieEntity) {
    val image = URLEncoder.encode(callie.image, StandardCharsets.UTF_8.toString())
    val callieWithoutImage = callie.copy(image = "")
    val callieJson = Json.encodeToString(callieWithoutImage)
    println("callie22 = $callieJson")
    navigate("$ROUTE_CHAT?callie=$callieJson&image=$image")
}