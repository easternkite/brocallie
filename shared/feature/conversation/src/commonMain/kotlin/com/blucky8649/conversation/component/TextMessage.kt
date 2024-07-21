package com.blucky8649.conversation.component

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
import com.blucky8649.conversation.AUTHOR_KIM
import com.blucky8649.conversation.AUTHOR_LEE
import com.blucky8649.conversation.Author
import com.blucky8649.designsystem.BcText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TextMessage(
    author: Author,
    message: String,
    modifier: Modifier = Modifier,
    isUserMe: Boolean = false,
    isAuthorRepeated: Boolean,
    onImageClick: (id: String) -> Unit
) {
    val spaceBetween = if (isAuthorRepeated)
        Modifier else Modifier.padding(top = 8.dp)
    
    Row(modifier = spaceBetween.then(modifier)) {
        if (!isAuthorRepeated) {
            ChatCircleImage(author = author, onClick = onImageClick)
        } else {
            Spacer(modifier = Modifier.width(74.dp))
        }
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
                .weight(1f),
        ) {
            if (!isAuthorRepeated) {
                AuthorName(author.name)
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
    author: Author,
    onClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    val borderColor = MaterialTheme.colorScheme.tertiary
    AsyncImage(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .size(42.dp)
            .border(1.5.dp, borderColor, CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
            .clip(CircleShape)
            .clickable(onClick = { onClick(author.id) })
            .then(modifier),
        model = author.image,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview
fun TextMessagePreview() {
    Column {
        TextMessage(
            author = AUTHOR_LEE,
            message = "Hi, nice to meet you.",
            isUserMe = true,
            isAuthorRepeated = false,
        ) {}
        
        TextMessage(
            author = AUTHOR_LEE,
            message = "How are you?",
            isUserMe = true,
            isAuthorRepeated = true,
        ) {}
        
        TextMessage(
            author = AUTHOR_KIM,
            message = "Hi!",
            isUserMe = false,
            isAuthorRepeated = false,
        ) {}
    }
}