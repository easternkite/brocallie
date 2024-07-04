package com.blucky8649.tts

import android.content.Context
import java.util.Locale
import android.speech.tts.TextToSpeech as AndroidTTS

class TTSAndroid(context: Context) : Speechable {
    private val listener = AndroidTTS.OnInitListener {
        tts.language = Locale.JAPAN
        tts.setSpeechRate(1.0f)
        tts.setPitch(1.0f)
    }
    override val tts: TextToSpeech = AndroidTTS(context, listener)

    override fun speak(text: String, language: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}