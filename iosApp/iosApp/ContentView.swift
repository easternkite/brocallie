import SwiftUI
import shared

struct ContentView: View {
    @State var showContactDetails: Bool = false
    @State var showCreateCallie: Bool = false
    @State var currentCallie: RoomCallieEntity? = nil

	var body: some View {
        NavigationStack {
            ContactScreen(
                onContactClick: { callie in
                    showContactDetails = true
                    currentCallie = callie
                },
                onAddButtonClick: {showCreateCallie = true }
            )
            .ignoresSafeArea(.container)
            .navigationDestination(isPresented: $showContactDetails) {
                if let callie = self.currentCallie {
                    ChatScreen(
                        callie: callie,
                        onBackPressed: {
                            showContactDetails = false
                            currentCallie = nil
                        },
                        onImageClicked: {_ in  }
                    )
                    .edgesIgnoringSafeArea(.all)
                    .ignoresSafeArea(.keyboard)
                    .navigationBarBackButtonHidden(true)
                }
            }
            .navigationDestination(isPresented: $showCreateCallie) {
                CreateCallieScreen { showCreateCallie = false}
                    .ignoresSafeArea(.container)
                    .navigationBarBackButtonHidden()
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
