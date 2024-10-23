package com.blucky8649.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import brocallie.shared.feature.profile.generated.resources.Res
import brocallie.shared.feature.profile.generated.resources.brand_deezer
import brocallie.shared.feature.profile.generated.resources.device_nintendo
import brocallie.shared.feature.profile.generated.resources.gender
import brocallie.shared.feature.profile.generated.resources.hobby
import brocallie.shared.feature.profile.generated.resources.ic_briefcase
import brocallie.shared.feature.profile.generated.resources.ic_category
import brocallie.shared.feature.profile.generated.resources.ic_gender_bigender
import brocallie.shared.feature.profile.generated.resources.job
import brocallie.shared.feature.profile.generated.resources.mood_happy
import brocallie.shared.feature.profile.generated.resources.personality
import brocallie.shared.feature.profile.generated.resources.tone
import brocallie.shared.feature.profile.generated.resources.type
import brocallie.shared.feature.profile.generated.resources.voice
import compose.icons.TablerIcons
import compose.icons.tablericons.User
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ProfileScreen(
    name: String,
    modifier: Modifier = Modifier,
    image: @Composable (Modifier) -> Unit,
    type: String = "",
    gender: String = "",
    hobby: String = "",
    job: String = "",
    personality: String = "",
    tone: String = "",
    voice: String = ""
) {
    LazyColumn(modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        item {
            image(
                Modifier
                    .fillMaxWidth()
                    .height(235.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            )
        }

        item {
            Box(Modifier.fillMaxWidth()) {
                NameSpace(
                    name = name,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = -(32).dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Column(Modifier.padding(horizontal = 16.dp).offset(y = -(32).dp)) {
                TraitCard(
                    title = stringResource(Res.string.type),
                    content = type,
                    startIcon = vectorResource(Res.drawable.ic_category)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TraitCard(
                    title = stringResource(Res.string.gender),
                    content = gender,
                    startIcon = vectorResource(Res.drawable.ic_gender_bigender)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TraitCard(
                    title = stringResource(Res.string.hobby),
                    content = hobby,
                    startIcon = vectorResource(Res.drawable.device_nintendo)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TraitCard(
                    title = stringResource(Res.string.job),
                    content = job,
                    startIcon = vectorResource(Res.drawable.ic_briefcase)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TraitCard(title = stringResource(Res.string.personality), content = personality)
                Spacer(modifier = Modifier.height(8.dp))
                TraitCard(
                    title = stringResource(Res.string.tone),
                    content = tone,
                    startIcon = vectorResource(Res.drawable.brand_deezer)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TraitCard(
                    title = stringResource(Res.string.voice),
                    content = voice,
                    startIcon = vectorResource(Res.drawable.mood_happy)
                )
            }
        }
    }
}

@Composable
fun NameSpace(
    name: String,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun TraitCard(
    title: String = "Title",
    content: String = "Content",
    startIcon: ImageVector = TablerIcons.User,
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    startIcon,
                    contentDescription = "",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Text(content, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}