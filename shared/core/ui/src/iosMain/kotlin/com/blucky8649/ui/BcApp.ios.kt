package com.blucky8649.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.blucky8649.room.databaseModule
import com.blucky8649.ui.navigation.BcNavHost
import com.easternkite.eungabi.navigation.rememberEunGabiController
import org.koin.compose.KoinApplication

@OptIn(ExperimentalCoilApi::class)
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

fun BcViewController() = ComposeUIViewController {
    val controller = rememberEunGabiController()
    BcNavHost(controller = controller)
}