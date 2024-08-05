import Foundation
import SwiftUI
import shared

struct ChatScreen: UIViewControllerRepresentable {
    let callie: RoomCallieEntity
    let onBackPressed: () -> Void
    let onImageClicked: (String) -> Void
    
    func makeUIViewController(context: Context) -> some UIViewController {
        ChatScreen_iosKt.ChatScreenViewController(
            callie: callie,
            onBackPressed: onBackPressed,
            onImageClick: onImageClicked
        )
    }
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
