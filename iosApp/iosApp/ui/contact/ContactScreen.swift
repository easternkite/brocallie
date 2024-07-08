import Foundation
import SwiftUI
import shared

struct ContactScreen: UIViewControllerRepresentable {
    let onContactClick: (String) -> Void
    
    func makeUIViewController(context: Context) -> some UIViewController {
        ContactsScreen_iosKt.ContactsViewController(onContactClick: onContactClick)
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}
}