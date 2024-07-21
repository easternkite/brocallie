import Foundation
import SwiftUI
import shared

struct ChatScreen: UIViewControllerRepresentable {
    let title: String
    let onBackPressed: () -> Void
    let onImageClicked: (String) -> Void
    
    func makeUIViewController(context: Context) -> some UIViewController {
        ChatScreen_iosKt.ChatScreenViewController(
            title: title,
            onBackPressed: onBackPressed,
            onImageClick: onImageClicked
        )
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
