package com.blucky8649.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.materialkolor.DynamicMaterialTheme
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@Composable
fun BcTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    LaunchedEffect(Unit) {
        Napier.base(DebugAntilog())
    }
    DynamicMaterialTheme(
        seedColor = Color(0xFC6FFDB),
        useDarkTheme = isDarkTheme,
        animate = true,
        content = content
    )
}