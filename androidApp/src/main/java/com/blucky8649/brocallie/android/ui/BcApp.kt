package com.blucky8649.brocallie.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.blucky8649.brocallie.android.navigation.BroCallieNavHost
import com.blucky8649.brocallie.android.ui.BcAppState
import com.blucky8649.brocallie.android.ui.rememberBcAppState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BroCallieApp(
    modifier: Modifier = Modifier,
    appState: BcAppState = rememberBcAppState()
) {
    MyApplicationTheme {
        Scaffold(
            modifier = modifier,
            containerColor = Color.Transparent
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                BroCallieNavHost(appState = appState)
            }
        }
    }
}