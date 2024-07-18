package com.blucky8649.conversation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.blucky8649.designsystem.BcText
import org.jetbrains.compose.ui.tooling.preview.Preview

private val ChatBubbleShape = RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp)

@Composable
fun ChatBubble(
    isUserMe: Boolean = false,
    messageText: String
) {
    val bubbleColor = if (isUserMe) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
    
    Surface(
        color = bubbleColor,
        shape = ChatBubbleShape
    ) {
        BcText(
            text = messageText,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview
fun ChatBubblePreview() {
    Column(Modifier.padding(5.dp)) {
        ChatBubble(true, "Hey there!")
        Spacer(Modifier.height(3.dp))
        ChatBubble(true, "I'm developing an interactive AI chat app.")
        Spacer(Modifier.height(10.dp))
        ChatBubble(false, "Hey! That's awesome!")
    }
}