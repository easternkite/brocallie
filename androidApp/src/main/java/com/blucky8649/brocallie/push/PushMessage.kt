package com.blucky8649.brocallie.push

import com.google.firebase.messaging.RemoteMessage

data class PushMessage(
    val title: String,
    val body: String,
    val imageUrl: String? = null,
    val channelId: String = ID_CHANNEL_AD.toString(),
)

fun RemoteMessage.toPushMessage() = PushMessage(
    title = notification?.title ?: "EMPTY",
    body = notification?.body ?: "EMPTY",
    imageUrl = notification?.imageUrl?.toString() ?: "",
    channelId = notification?.channelId ?: ID_CHANNEL_AD.toString(),
)