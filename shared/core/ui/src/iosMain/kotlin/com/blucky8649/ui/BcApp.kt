package com.blucky8649.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.blucky8649.room.databaseModule
import org.koin.compose.KoinApplication

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BcApp(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
        }
        KoinApplication(
            application = {
                modules(databaseModule)
            }, content)
    }
}