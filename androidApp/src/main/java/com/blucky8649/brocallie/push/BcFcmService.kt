package com.blucky8649.brocallie.push

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.toBitmap
import com.blucky8649.brocallie.R
import com.blucky8649.brocallie.android.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BcFcmService : FirebaseMessagingService() {
    companion object {
        const val TAG = "BcFcmService"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "onMessageReceived from: ${message.from}")

        val pendingIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }.let { PendingIntent.getActivity(this, 0, it, PendingIntent.FLAG_IMMUTABLE) }

        CoroutineScope(Dispatchers.IO).launch {
            val pushMessage = message.toPushMessage()
            val iconBitmap = getPushImage(pushMessage.imageUrl ?: "")

            val messageBuilder = NotificationCompat
                .Builder(this@BcFcmService, pushMessage.channelId)
                .setSmallIcon(R.drawable.ic_push_brocallie)
                .setColor(getColor(R.color.PUSH_GREEN))
                .setLargeIcon(iconBitmap)
                .setContentTitle(pushMessage.title)
                .setContentText(pushMessage.body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            withContext(Dispatchers.Main) {
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                NotificationManagerCompat.from(this@BcFcmService).apply {
                    notificationManager.notify(System.currentTimeMillis().toInt(), messageBuilder.build())
                }
            }
        }
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(TAG, "onDeletedMessages")
    }
}

suspend fun BcFcmService.getPushImage(url: String): Bitmap? {
    val loader = ImageLoader(this)
    val request = ImageRequest.Builder(this)
        .data(url)
        .allowHardware(false)
        .build()

    return loader.execute(request)
        .image
        ?.toBitmap()
}