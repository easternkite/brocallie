package com.blucky8649.conversation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import brocallie.shared.feature.conversation.generated.resources.Res
import brocallie.shared.feature.conversation.generated.resources.send
import brocallie.shared.feature.conversation.generated.resources.textfield_hint
import com.blucky8649.designsystem.BcText
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserInputField(
    resetScroll: () -> Unit,
    modifier: Modifier = Modifier,
    onMessageSent: (String) -> Unit
) {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var textFieldFocusState by remember { mutableStateOf(false) }

    val messageSent = {
        onMessageSent(textState.text)
        textState = TextFieldValue()
        resetScroll()
    }

    Surface(tonalElevation = 2.dp, contentColor = MaterialTheme.colorScheme.secondary) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.fillMaxSize().weight(1f)) {
                ChatInputField(
                    textFieldValue = textState,
                    onTextChanged = { textState = it },
                    onTextFieldFocused = { textFieldFocusState = it },
                    focusState = textFieldFocusState,
                    onMessageSent = { messageSent() },
                    resetScroll = resetScroll
                )
            }
            SendButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                enabled = textState.text.isNotBlank(),
                onMessageSent = { messageSent() }
            )
        }
    }
}

@Composable
fun SendButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onMessageSent: () -> Unit
) {
    val disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
    val buttonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Color.Transparent,
        disabledContentColor = disabledContentColor
    )
    val border = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
    ).takeIf { !enabled }

    Button(
        modifier = modifier.height(36.dp),
        enabled = enabled,
        onClick = onMessageSent,
        colors = buttonColors,
        border = border,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            stringResource(Res.string.send),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun BoxScope.ChatInputField(
    modifier: Modifier = Modifier,
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onTextFieldFocused: (Boolean) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    focusState: Boolean,
    resetScroll: () -> Unit,
    onMessageSent: (String) -> Unit,
) {
    var lastFocusState by remember { mutableStateOf(false) }
    BasicTextField(
        value = textFieldValue,
        onValueChange = onTextChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp)
            .align(Alignment.CenterStart)
            .onFocusChanged { state ->
                if (lastFocusState != state.isFocused) {
                    onTextFieldFocused(state.isFocused)
                }
                lastFocusState = state.isFocused
            },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions {
            if (textFieldValue.text.isNotBlank()) {
                resetScroll()
                onMessageSent(textFieldValue.text)
            }
        },
        singleLine = true,
        maxLines = 1,
        cursorBrush = SolidColor(LocalContentColor.current),
        textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current)
    )

    val disableContentColor =
        MaterialTheme.colorScheme.onSurfaceVariant
    if (textFieldValue.text.isEmpty() && !focusState) {
        BcText(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 32.dp),
            text = stringResource(Res.string.textfield_hint),
            style = MaterialTheme.typography.bodyLarge.copy(color = disableContentColor)
        )
    }
}