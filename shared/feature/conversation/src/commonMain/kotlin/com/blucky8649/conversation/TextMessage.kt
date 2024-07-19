package com.blucky8649.conversation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.blucky8649.designsystem.BcText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TextMessage(
    modifier: Modifier = Modifier,
    authorName: String,
    message: String,
    imageUrl: String = "",
    isUserMe: Boolean = false,
    isAuthorRepeated: Boolean,
    onImageClick: () -> Unit
) {
    val spaceBetween = if (isAuthorRepeated)
        Modifier else Modifier.padding(top = 8.dp)
    
    Row(modifier = spaceBetween.then(modifier)) {
        if (!isAuthorRepeated) {
            ChatCircleImage(imageUrl = imageUrl, onClick = onImageClick)   
        } else {
            Spacer(modifier = Modifier.width(74.dp))
        }
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f),
        ) {
            if (!isAuthorRepeated) {
                AuthorName(authorName)
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
            ChatBubble(isUserMe, message)
        }
    }    
}

@Composable
private fun AuthorName(
    name: String
) {
    Row {
        BcText(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .alignBy(LastBaseline)
                .paddingFrom(LastBaseline, after = 8.dp)
        )      
    }
}

@Composable
private fun ChatCircleImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String = "",
    onClick: () -> Unit
) {
    val borderColor = MaterialTheme.colorScheme.tertiary
    AsyncImage(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .size(42.dp)
            .border(1.5.dp, borderColor, CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .then(modifier),
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview
fun TextMessagePreview() {
    Column {
        TextMessage(
            authorName = "Lee",
            message = "Hi, nice to meet you.",
            isUserMe = true,
            isAuthorRepeated = false,
        ) {}
        
        TextMessage(
            authorName = "Lee",
            message = "How are you?",
            isUserMe = true,
            isAuthorRepeated = true,
        ) {}
        
        TextMessage(
            authorName = "Kim",
            message = "Hi!",
            isUserMe = false,
            isAuthorRepeated = false,
        ) {}
    }
}