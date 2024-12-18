package com.blucky8649.brocallie.android

import android.Manifest
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.blucky8649.brocallie.R
import com.blucky8649.brocallie.push.ID_CHANNEL_AD
import com.blucky8649.brocallie.push.ID_CHANNEL_CHAT
import com.blucky8649.brocallie.push.PushChannel
import com.blucky8649.brocallie.push.PushImportance
import com.blucky8649.brocallie.push.toNotificationChannel
import com.blucky8649.ui.navigation.BcNavHost
import com.easternkite.eungabi.navigation.rememberEunGabiController

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        Log.d("MainActivity", "isPermissionGranted: $isGranted")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        checkPushPermissionOver33()
        createPushChannels()
        setContent {
            val controller = rememberEunGabiController()
            BcNavHost(controller = controller)
        }
    }

    private fun checkPushPermissionOver33() {
        val is33OrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        if (!is33OrHigher) return

        val permissionReq = ContextCompat
            .checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)

        when {
            permissionReq == PackageManager.PERMISSION_GRANTED -> {
                Log.d("MainActivity", "isPermissionGranted")
            }
            else -> requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun createPushChannels() {
        val isOreoOrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        if (!isOreoOrHigher) return

        val channelAd = PushChannel(
            id = ID_CHANNEL_AD,
            name = getString(R.string.channel_ad),
            importance = PushImportance.DEFAULT,
        )

        val channelChat = PushChannel(
            id = ID_CHANNEL_CHAT,
            name = getString(R.string.channel_chat),
            importance = PushImportance.HIGH,
        )

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channels = listOf(channelAd, channelChat).map(PushChannel::toNotificationChannel)
        manager.createNotificationChannels(channels)
    }
}

@Composable
@Preview(showSystemUi = true)
fun BroCallieAppPreview() {

}