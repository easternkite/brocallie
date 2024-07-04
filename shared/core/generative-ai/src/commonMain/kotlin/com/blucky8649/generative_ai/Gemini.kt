package com.blucky8649.generative_ai

import com.blucky8649.brocallie.shared.`generative-ai`.BuildKonfig
import dev.shreyaspatil.ai.client.generativeai.GenerativeModel

object Gemini{
    val generativeModel get() = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildKonfig.API_KEY,
    )

    suspend fun go() {
//        val prompt = "ㅁㄴㅇㄹㅁㄴㅇㅁㄹㅁㄴㅇㄹ"
//        val response = generativeModel.generateContent(prompt)
//        print(response.text)
    }
}