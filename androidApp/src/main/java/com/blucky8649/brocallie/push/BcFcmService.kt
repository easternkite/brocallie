package com.blucky8649.brocallie.push

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.blucky8649.brocallie.R
import com.blucky8649.brocallie.android.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

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

        val pushMessage = message.toPushMessage()
        val messageBuilder = NotificationCompat
            .Builder(this, ID_CHANNEL_AD.toString())
            .setSmallIcon(R.drawable.ic_push_brocallie)
            .setColor(Color(0xFFC6FFDB).toArgb())
            .setContentTitle(pushMessage.title)
            .setContentText(pushMessage.body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        NotificationManagerCompat.from(this).apply {
            notificationManager.notify(System.currentTimeMillis().toInt(), messageBuilder.build())
        }
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(TAG, "onDeletedMessages")
    }
}