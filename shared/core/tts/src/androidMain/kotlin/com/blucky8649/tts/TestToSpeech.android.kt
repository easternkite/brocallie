package com.blucky8649.tts

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual typealias TextToSpeech = TextToSpeech

@Composable
actual fun rememberTts(): Speechable {
    val context = LocalContext.current
    return remember { TTSAndroid(context) }
}