package com.blucky8649.generative_ai

import com.blucky8649.brocallie.shared.`generative-ai`.BuildKonfig
import dev.shreyaspatil.ai.client.generativeai.GenerativeModel
import dev.shreyaspatil.ai.client.generativeai.type.BlockThreshold
import dev.shreyaspatil.ai.client.generativeai.type.HarmCategory
import dev.shreyaspatil.ai.client.generativeai.type.SafetySetting

object Gemini{

    val generativeModel get() = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildKonfig.API_KEY,
        safetySettings = HarmCategory.entries
            .filter { it != HarmCategory.UNKNOWN }
            .map { SafetySetting(it, BlockThreshold.UNSPECIFIED) }
    )
}