package com.blucky8649.firebase

import cocoapods.FirebaseStorage.FIRStorage
import cocoapods.FirebaseStorage.FIRStorageMetadata
import io.ktor.utils.io.errors.PosixException
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding

@OptIn(ExperimentalForeignApi::class)
val storage = FIRStorage.storageWithURL(BUCKET_URL)




@OptIn(ExperimentalForeignApi::class)
actual fun ByteArray.uploadImage(
    imageName: String,
    completion: (url: String?) -> Unit
) {
    val storageRef = storage.reference()
    val metaData = FIRStorageMetadata().apply {
        setContentType("image/jpeg")
    }

    val imageData = memScoped {
        val string = NSString.create(string = decodeToString())
        string.dataUsingEncoding(NSUTF8StringEncoding)
    } ?: return

    val imageRef = storageRef.child("images/$imageName")
    imageRef.putData(imageData, metadata = metaData) { metadata, error ->
        metadata ?: return@putData
        imageRef.downloadURLWithCompletion { nsurl, _ ->
            val url = nsurl?.absoluteString()
            completion.invoke(url)
            error?.also { throw PosixException.NoSuchFileException(it.localizedDescription) }
        }
    }
}