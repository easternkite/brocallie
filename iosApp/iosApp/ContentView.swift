import SwiftUI
import shared

struct ContentView: View {
    @State var showContactDetails: Bool = false

	var body: some View {
        NavigationStack {
            ContactScreen { name in
                showContactDetails = true
            }
            .navigationDestination(isPresented: $showContactDetails) {
                ContactDetailScreen(
                    onBackPressed: { showContactDetails = false },
                    onSaveButtonClicked: {}
                )
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
