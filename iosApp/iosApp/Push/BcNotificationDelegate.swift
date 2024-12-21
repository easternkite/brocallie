import FirebaseMessaging
import UserNotifications

class BcNotificationDelegate: NSObject, ObservableObject {
    override init() {
        super.init()
        registerForUnUserNotificationCenter()
        registerForMessaging()
    }
}

extension BcNotificationDelegate: UNUserNotificationCenterDelegate {
    private func registerForUnUserNotificationCenter() {
        UNUserNotificationCenter.current().delegate = self
        registerAuthorization()
    }
    
    private func registerAuthorization() {
        let options: UNAuthorizationOptions = [.alert, .badge, .sound]
        UNUserNotificationCenter.current().requestAuthorization(options: options) { granted, _ in
            print("Permission granted: \(granted)")
            guard granted else { return }
        }
    }
    
    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        willPresent notification: UNNotification,
        withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void
    ) {
        completionHandler([.banner, .sound, .badge])
    }

    func userNotificationCenter(
        _ center: UNUserNotificationCenter,
        didReceive response: UNNotificationResponse,
        withCompletionHandler completionHandler: @escaping () -> Void
    ) {
        let userInfo = response.notification.request.content.userInfo
        print("User Info: \(userInfo)")
        completionHandler()
    }
}

extension BcNotificationDelegate: MessagingDelegate {
    private func registerForMessaging() {
        Messaging.messaging().delegate = self
    }
    
    func messaging(_ messaging: Messaging, didReceiveRegistrationToken fcmToken: String?) {
        print("FCM Token: \(fcmToken ?? "")")
    }
}
