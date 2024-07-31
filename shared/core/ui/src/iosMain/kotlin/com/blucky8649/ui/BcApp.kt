package com.blucky8649.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.blucky8649.room.databaseModule
import org.koin.compose.KoinApplication

@Composable
fun BcApp(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        KoinApplication(
            application = {
                modules(databaseModule)
            }, content)
    }
}