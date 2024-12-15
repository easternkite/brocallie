package com.blucky8649.brocallie.client

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging

fun BcClient(
    engine: HttpClientEngine = CIO.create(),
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient {
    return HttpClient(engine) {
        expectSuccess = true
        install(Logging) {
            logger = Logger()
            level = LogLevel.ALL
        }
        install(DefaultRequest)
        block()
    }.also { Napier.base(DebugAntilog()) }
}