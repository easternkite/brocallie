import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.window.ComposeUIViewController
import com.blucky8649.createcallie.CreateCallieScreen
import com.blucky8649.designsystem.BcTopAppBar
import com.blucky8649.ui.BcApp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinContext
import platform.Foundation.NSLocaleLanguageCode

@OptIn(ExperimentalMaterial3Api::class)
fun CreateCallieViewController(
    onBackPressed: () -> Unit,
) = ComposeUIViewController {
    KoinContext {
        CreateCallieScreen(
            onBackButtonPressed = onBackPressed,
            onCreateClick = onBackPressed,
            languageCode = NSLocaleLanguageCode.toString()
        )
    }
}