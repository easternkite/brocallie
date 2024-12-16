package com.blucky8649.firebase

import com.blucky8649.brocallie.client.BcClient
import com.blucky8649.brocallie.client.postRequest
import com.blucky8649.brocallie.shared.core.firebase.BuildKonfig
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual suspend fun ByteArray.uploadImage(
    imageName: String,
    completion: (String?) -> Unit
) { uploadToStorage(imageName, completion) }

suspend fun ByteArray.uploadToStorage(
    imageName: String,
    completion: (String?) -> Unit
) = withContext(Dispatchers.IO) {
    val client = BcClient()
    val imageFullPath = "images%2F$imageName"
    val storageBucket = BuildKonfig.STORAGE_BUCKET
    val url = "$BASE_URL/b/$storageBucket/o/$imageFullPath?alt=media"

    val request = client.postRequest<String>(
        urlString = url,
        block = {
            setBody(this@uploadToStorage)
        }
    )

    request
        .onSuccess { completion(url) }
        .onFailure {
            it.printStackTrace()
            completion(null)
        }
}