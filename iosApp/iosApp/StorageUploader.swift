//
//  StorageUploader.swift
//  iosApp
//
//  Created by easternkite on 12/12/24.
//  Copyright © 2024 orgName. All rights reserved.
//
import shared
import FirebaseStorage

class IOSStorageUploader: StorageUploader {
    let storage = Storage.storage()
    func uploadImage(imageData: Data, imageName: String, completion: @escaping (String?) -> Void) {
        let storageRef = storage.reference()
        let metaData = StorageMetadata()
        metaData.contentType = "image/jpeg"

        let imageRef = storageRef.child("images/\(imageName)")
        imageRef.putData(imageData, metadata: metaData) { (_, _) in
            imageRef.downloadURL { (url, _) in
                completion(url?.absoluteString)
            }
        }
    }
}
