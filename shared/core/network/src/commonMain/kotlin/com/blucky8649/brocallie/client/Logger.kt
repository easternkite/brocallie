package com.blucky8649.brocallie.client

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger

class Logger : Logger {
    override fun log(message: String) {
        Napier.v(message = message, tag = "HttpClientLogger")
    }
}