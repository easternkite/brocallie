package com.blucky8649.tts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

expect class TextToSpeech

@Composable
expect fun rememberTts(): Speechable

val LocalTts = compositionLocalOf<Speechable> { error("No Tts provided") }