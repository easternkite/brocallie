package com.blucky8649.brocallie.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post

suspend inline fun <reified T>HttpClient.postRequest(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): Result<T> = runCatching { post(urlString) { apply(block) }.body() }

suspend inline fun <reified T>HttpClient.getRequest(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): Result<T> = runCatching { get(urlString) { apply(block) }.body() }
