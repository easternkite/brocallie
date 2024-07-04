package com.blucky8649.tts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.AVFAudio.AVSpeechSynthesizer

actual typealias TextToSpeech = AVSpeechSynthesizer

@Composable
actual fun rememberTts(): Speechable {
    return remember { TtsIOS() }
}