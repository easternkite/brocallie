package com.blucky8649.createcallie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import brocallie.shared.feature.createcallie.generated.resources.Res
import brocallie.shared.feature.createcallie.generated.resources.add_photo
import brocallie.shared.feature.createcallie.generated.resources.photo_ai
import brocallie.shared.feature.createcallie.generated.resources.tooltip_content
import brocallie.shared.feature.createcallie.generated.resources.tooltip_title
import com.blucky8649.designsystem.BcText
import com.blucky8649.designsystem.BcTopAppBar
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import compose.icons.TablerIcons
import compose.icons.tablericons.Check
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val TooltipShape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCallieScreen(
    onBackButtonPressed: () -> Unit,
    onCreateClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            BcTopAppBar(
                title = "Create Callie",
                navigationIcon = Icons.AutoMirrored.Default.ArrowBack,
                onNavigationClick = onBackButtonPressed,
                actionIcon = TablerIcons.Check,
                onActionClick = onCreateClick,
            )
        },
        bottomBar = {
            Box(modifier = Modifier.padding(10.dp)) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = TooltipShape,
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        BcText(
                            text = stringResource(Res.string.tooltip_title),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        BcText(
                            text = stringResource(Res.string.tooltip_content),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    ) { paddingValues: PaddingValues ->
        var imageBitmap: ImageBitmap? by remember { mutableStateOf(null) }

        val scope = rememberCoroutineScope()
        val picker = rememberImagePickerLauncher(
            selectionMode = SelectionMode.Single,
            scope = scope,
            onResult = { imageBitmap = it.firstOrNull()?.toImageBitmap() }
        )

        val circleModifier = Modifier
            .size(300.dp)
            .clip(CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
            .clickable { picker.launch() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            imageBitmap?.also {
                Image(
                    bitmap = it,
                    contentDescription = "selected image",
                    modifier = circleModifier,
                    contentScale = ContentScale.Crop
                )
            } ?: also {
                Column(
                    modifier = circleModifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.photo_ai),
                        contentDescription = "add photo",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(150.dp)
                    )
                    Text(
                        text = stringResource(Res.string.add_photo),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CreateCallieScreenPreview() {
    CreateCallieScreen(
        onBackButtonPressed = {},
        onCreateClick = {}
    )
}