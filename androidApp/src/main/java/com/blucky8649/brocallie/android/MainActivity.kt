package com.blucky8649.brocallie.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.blucky8649.brocallie.android.navigation.BroCallieNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BroCallieApp {
                BroCallieNavHost()
            }
        }
    }
}