package com.blucky8649.firebase

import com.blucky8649.brocallie.shared.core.firebase.BuildKonfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

val bucketUrl = "gs://${BuildKonfig.STORAGE_BUCKET}"
val storage = Firebase.storage(bucketUrl)

actual suspend fun ByteArray.uploadImage(
    imageName: String,
    completion: (String?) -> Unit
) {
    val storageRef = storage.reference
    val imageRef = storageRef.child("images/$imageName")

    imageRef.putBytes(this)
        .addOnFailureListener {
            it.localizedMessage ?: return@addOnFailureListener
            throw NoSuchElementException(it.localizedMessage)
        }.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.also { throw it }
            }
            imageRef.downloadUrl
        }.addOnCompleteListener {
            if (it.isSuccessful) {
                completion(it.result.toString())
            } else {
                completion(null)
            }
        }
}