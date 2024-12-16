package com.blucky8649.firebase

const val BASE_URL = "https://firebasestorage.googleapis.com/v0"

expect suspend fun ByteArray.uploadImage(
    imageName: String,
    completion: (String?) -> Unit
)