import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.ComposeUIViewController
import com.blucky8649.createcallie.CreateCallieScreen
import org.koin.compose.KoinContext
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

@OptIn(ExperimentalMaterial3Api::class)
fun CreateCallieViewController(
    onBackPressed: () -> Unit,
) = ComposeUIViewController {
    KoinContext {
        CreateCallieScreen(
            onBackButtonPressed = onBackPressed,
            onCreateClick = onBackPressed,
            languageCode = NSLocale.currentLocale.languageCode
        )
    }
}