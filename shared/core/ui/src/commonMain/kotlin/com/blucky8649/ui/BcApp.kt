package com.blucky8649.ui

import androidx.compose.runtime.Composable

@Composable
expect fun BcApp(
    content: @Composable () -> Unit
)