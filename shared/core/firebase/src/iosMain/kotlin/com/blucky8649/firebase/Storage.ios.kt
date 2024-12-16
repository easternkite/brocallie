package com.blucky8649.firebase

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create

interface StorageUploader {
    fun uploadImage(
        imageData: NSData,
        imageName: String,
        completion: (url: String?) -> Unit
    )
}

object IOSUploader {
    var listener: StorageUploader? = null
}

actual suspend fun ByteArray.uploadImage(
    imageName: String,
    completion: (url: String?) -> Unit
) {
    IOSUploader.listener?.uploadImage(toNSData(), imageName, completion)
}

@OptIn(ExperimentalForeignApi::class)
fun ByteArray.toNSData(): NSData {
    return this.usePinned { pinned ->
        NSData.create(bytes = pinned.addressOf(0), length = this.size.toULong())
    }
}
