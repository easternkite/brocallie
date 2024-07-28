package com.blucky8649.firebase

const val BUCKET_URL = "gs://brocallie.appspot.com"

expect fun ByteArray.uploadImage(
    imageName: String,
    completion: (String?) -> Unit
)