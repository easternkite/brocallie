package com.blucky8649.profile.navigation

import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.blucky8649.profile.ProfileScreen
import com.blucky8649.room.model.CallieEntity
import com.easternkite.eungabi.navigation.EunGabiController
import com.easternkite.eungabi.navigation.EunGabiGraphBuilder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.thauvin.erik.urlencoder.UrlEncoderUtil

const val ROUTE_PROFIlE = "route_profile"
const val KEY_CALLIE = "key_callie"
const val KEY_IMAGE = "key_image"


fun EunGabiGraphBuilder.profileRoute(
    onBackPressed: () -> Unit,
) {
    composable(ROUTE_PROFIlE) {
        val callieJson = it.arguments.getString(KEY_CALLIE)
        val image = it.arguments.getString(KEY_IMAGE)

        val callie = callieJson
            ?.let { Json.decodeFromString<CallieEntity>(it) }
            ?.copy(image = image ?: "")
            ?: CallieEntity()

        ProfileScreen(
            name = callie.name,
            onBackPressed = onBackPressed,
            image = { modifier ->
                AsyncImage(
                    modifier = modifier,
                    contentScale = ContentScale.Crop,
                    model = callie.image,
                    contentDescription = "the profile image of ${callie.name}"
                )
            },
            type = callie.type,
            gender = callie.gender,
            job = callie.job,
            age = callie.age,
            hobby = callie.hobby,
            personality = callie.personality,
            tone = callie.tone,
            voice = callie.voice
        )
    }
}

fun EunGabiController.navigateToProfile(callie: CallieEntity) {
    val image = UrlEncoderUtil.encode(callie.image)
    val callieWithoutImage = callie.copy(image = "")
    val callieJson = Json.encodeToString(callieWithoutImage)
    navigate("$ROUTE_PROFIlE?$KEY_CALLIE=$callieJson&$KEY_IMAGE=$image")
}