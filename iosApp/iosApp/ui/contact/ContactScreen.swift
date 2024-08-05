import Foundation
import SwiftUI
import shared

struct ContactScreen: UIViewControllerRepresentable {
    let onContactClick: (RoomCallieEntity) -> Void
    let onAddButtonClick: () -> Void
    
    func makeUIViewController(context: Context) -> some UIViewController {
        ContactsScreen_iosKt.ContactsViewController(
            onContactClick: onContactClick,
            onAddButtonClick: onAddButtonClick
        )
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}
