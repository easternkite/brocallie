package com.blucky8649.firebase

import com.blucky8649.firebase.BUCKET_URL
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.nio.file.NoSuchFileException

val storage = Firebase.storage(BUCKET_URL)

actual fun ByteArray.uploadImage(
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