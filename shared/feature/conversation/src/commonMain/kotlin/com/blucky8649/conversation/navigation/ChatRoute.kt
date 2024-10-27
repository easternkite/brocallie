package com.blucky8649.conversation.navigation

import com.blucky8649.conversation.ChatScreen
import com.blucky8649.room.model.CallieEntity
import com.easternkite.eungabi.navigation.EunGabiController
import com.easternkite.eungabi.navigation.EunGabiGraphBuilder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.thauvin.erik.urlencoder.UrlEncoderUtil

const val ROUTE_CHAT = "route_chat"
const val KEY_CALLIE = "key_callie"
const val KEY_IMAGE = "key_image"

fun EunGabiGraphBuilder.chatScreenRoute(
    onBackPressed: () -> Unit,
    onImageClick: (callie: CallieEntity) -> Unit
) {
    composable(ROUTE_CHAT) {
        val callieJson = it.arguments.getString(KEY_CALLIE)
        val image = it.arguments.getString(KEY_IMAGE)

        val callie = callieJson
            ?.let { Json.decodeFromString<CallieEntity>(it) }
            ?.copy(image = image ?: "")
            ?: CallieEntity()

        ChatScreen(
            callie = callie,
            onBackPressed = onBackPressed,
            onImageClick = { onImageClick(callie) }
        )
    }
}


fun EunGabiController.navigateToChat(callie: CallieEntity) {
    val image = UrlEncoderUtil.encode(callie.image)
    val callieWithoutImage = callie.copy(image = "")
    val callieJson = Json.encodeToString(callieWithoutImage)
    navigate("$ROUTE_CHAT?$KEY_CALLIE=$callieJson&$KEY_IMAGE=$image")
}