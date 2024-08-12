package com.blucky8649.conversation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blucky8649.designsystem.BcText
import org.jetbrains.compose.ui.tooling.preview.Preview

private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)
private val ChatBubbleShapeFromMe = RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp)
private val ChatBubbleShapeRepeated = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    isUserMe: Boolean = false,
    isAuthorRepeated: Boolean = false,
    messageText: String,
) {
    ChatBubble(
        modifier,
        isUserMe,
        isAuthorRepeated
    ) {
        BcText(
            text = messageText,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    isUserMe: Boolean = false,
    isAuthorRepeated: Boolean = false,
    content: @Composable () -> Unit,
) {
    val bubbleColor = if (isUserMe) { MaterialTheme.colorScheme.primary }
    else { MaterialTheme.colorScheme.surfaceVariant }

    val shape = when {
        isAuthorRepeated -> ChatBubbleShapeRepeated
        isUserMe -> ChatBubbleShapeFromMe
        else -> ChatBubbleShape
    }

    Surface(
        modifier = modifier,
        color = bubbleColor,
        shape = shape,
        content = content
    )
}

@Composable
@Preview
fun ChatBubblePreview() {
    Column(Modifier.padding(5.dp)) {
        ChatBubble(isUserMe = true, messageText = "Hey there!")
        Spacer(Modifier.height(3.dp))
        ChatBubble(isUserMe = true, messageText = "I'm developing an interactive AI chat app.")
        Spacer(Modifier.height(10.dp))
        ChatBubble(isUserMe =false, messageText = "Hey! That's awesome!")
    }
}