import SwiftUI
import shared

struct ContentView: View {
    @State var showContactDetails: Bool = false

	var body: some View {
        NavigationStack {
            ContactScreen { name in
                showContactDetails = true
            }.ignoresSafeArea(.container)
            .navigationDestination(isPresented: $showContactDetails) {
                ChatScreen(title: "Chat", onBackPressed: { showContactDetails = false }, onImageClicked: {it in it }).ignoresSafeArea(.container)
                .navigationBarBackButtonHidden(true)
            }
        }
	}
}

#Preview {
    ContentView()
}

/**
 * Swipe - Back 강제 활성화
 */
extension UINavigationController: UIGestureRecognizerDelegate {
    override open func viewDidLoad() {
        super.viewDidLoad()
        interactivePopGestureRecognizer?.delegate = self
    }

    public func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        return viewControllers.count > 1
    }
}
