package com.blucky8649.firebase

import cocoapods.FirebaseStorage.FIRStorage
import cocoapods.FirebaseStorage.FIRStorageMetadata
import io.ktor.utils.io.errors.PosixException
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData

import platform.Foundation.create

@OptIn(ExperimentalForeignApi::class)
val storage = FIRStorage.storageWithURL(BUCKET_URL)




@OptIn(ExperimentalForeignApi::class)
actual fun ByteArray.uploadImage(
    imageName: String,
    completion: (url: String?) -> Unit
) {
    println("upload image called")
    val storageRef = storage.reference()
    val metaData = FIRStorageMetadata().apply {
        setContentType("image/jpeg")
    }

    val imageData = toNSData()

    println("imageData created = $imageData")
    val imageRef = storageRef.child("images/$imageName")
    imageRef.putData(imageData, metadata = metaData) { metadata, error ->
        metadata ?: return@putData
        imageRef.downloadURLWithCompletion { nsurl, _ ->
            val url = nsurl?.absoluteString()
            println("url = $url")
            completion.invoke(url)
            error?.also { throw PosixException.NoSuchFileException(it.localizedDescription) }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
fun ByteArray.toNSData(): NSData {
    return this.usePinned { pinned ->
        NSData.create(bytes = pinned.addressOf(0), length = this.size.toULong())
    }
}