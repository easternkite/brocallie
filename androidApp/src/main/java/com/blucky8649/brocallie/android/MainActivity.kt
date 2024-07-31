package com.blucky8649.brocallie.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.blucky8649.brocallie.android.ui.BroCallieApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BroCallieApp()
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun BroCallieAppPreview() {
    BroCallieApp()
}