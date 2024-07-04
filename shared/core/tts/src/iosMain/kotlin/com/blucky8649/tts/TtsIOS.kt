package com.blucky8649.tts

import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance

class TtsIOS : Speechable {
    override val tts: TextToSpeech = AVSpeechSynthesizer()

    override fun speak(text: String, language: String) {
        val utterance = AVSpeechUtterance(string = text)
        tts.speakUtterance(utterance)
    }
}