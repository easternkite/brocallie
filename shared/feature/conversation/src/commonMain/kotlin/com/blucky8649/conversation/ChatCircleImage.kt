package com.blucky8649.conversation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ChatCircleImage(
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