package com.blucky8649.tts

interface Speechable {
    val tts: TextToSpeech
    fun speak(text: String, language: String)
}