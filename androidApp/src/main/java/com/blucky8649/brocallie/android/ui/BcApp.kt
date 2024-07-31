package com.blucky8649.brocallie.android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.blucky8649.brocallie.android.navigation.BroCallieNavHost
import com.blucky8649.ui.BcApp

@Composable
fun BroCallieApp(
    modifier: Modifier = Modifier,
    appState: BcAppState = rememberBcAppState()
) {
    BcApp {
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