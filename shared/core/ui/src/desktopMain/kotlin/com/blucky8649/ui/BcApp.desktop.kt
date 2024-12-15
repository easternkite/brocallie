package com.blucky8649.ui

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import com.blucky8649.room.databaseModule
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.koin.compose.KoinApplication

@Composable
actual fun BcApp(
    content: @Composable () -> Unit
) {
    BcTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
        }
        KoinApplication(
            application = {
                modules(databaseModule)
            }, content)
    }
}