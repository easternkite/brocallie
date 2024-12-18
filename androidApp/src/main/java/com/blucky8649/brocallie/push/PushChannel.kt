package com.blucky8649.brocallie.push

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi

data class PushChannel(
    val id: Int,
    val name: String,
    val importance: PushImportance,
)

@RequiresApi(Build.VERSION_CODES.O)
fun PushChannel.toNotificationChannel() = NotificationChannel(
    id.toString(),
    name,
    importance.value
)

enum class PushImportance(val value: Int) {
    DEFAULT(NotificationManager.IMPORTANCE_DEFAULT),
    HIGH(NotificationManager.IMPORTANCE_HIGH),
    LOW(NotificationManager.IMPORTANCE_LOW),
    MAX(NotificationManager.IMPORTANCE_MAX),
    MIN(NotificationManager.IMPORTANCE_MIN),
    NONE(NotificationManager.IMPORTANCE_NONE),
    UNSPECIFIED(NotificationManager.IMPORTANCE_UNSPECIFIED),
}
