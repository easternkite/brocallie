import Foundation
import SwiftUI
import shared

struct ContactDetailScreen: UIViewControllerRepresentable {
    let onBackPressed: () -> Void
    let onSaveButtonClicked: () -> Void
    
    func makeUIViewController(context: Context) -> some UIViewController {
        ContactDetailScreen_iosKt.ContactDetailViewController(
            onBackPressed: onBackPressed,
            onSaveButtonClicked: onSaveButtonClicked
        )
    
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}

