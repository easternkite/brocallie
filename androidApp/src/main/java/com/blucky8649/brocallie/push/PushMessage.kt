package com.blucky8649.brocallie.push

import com.google.firebase.messaging.RemoteMessage

data class PushMessage(
    val title: String,
    val body: String,
    val imageUrl: String? = null,
    val channelId: String = ID_CHANNEL_AD.toString(),
)

fun RemoteMessage.toPushMessage() = PushMessage(
    title = data["title"] ?: "EMPTY",
    body = data["body"] ?: "EMPTY",
    imageUrl = data["imageUrl"] ?: "",
    channelId = data["channelId"] ?: ID_CHANNEL_AD.toString(),
)