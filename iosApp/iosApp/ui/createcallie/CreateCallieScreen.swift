//
//  CreateCallieScreen.swift
//  iosApp
//
//  Created by 이동연 on 8/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct CreateCallieScreen: UIViewControllerRepresentable {
    let onBackPressed: () -> Void
    
    func makeUIViewController(context: Context) -> some UIViewController {
        CreateCallieScreen_iosKt.CreateCallieViewController(onBackPressed: onBackPressed)
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}



