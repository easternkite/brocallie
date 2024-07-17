package com.blucky8649.contactdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import brocallie.shared.feature.contactdetails.generated.resources.Res
import brocallie.shared.feature.contactdetails.generated.resources.feature_contact_detail_contact
import brocallie.shared.feature.contactdetails.generated.resources.feature_contact_detail_name
import brocallie.shared.feature.contactdetails.generated.resources.feature_contact_detail_nickname
import brocallie.shared.feature.contactdetails.generated.resources.feature_contact_detail_save
import com.blucky8649.designsystem.BcText
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ContactDetailScreen(
    onBackPressed: () -> Unit,
    onSaveButtonClicked: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            ,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { BcText(stringResource(Res.string.feature_contact_detail_name)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { BcText(stringResource(Res.string.feature_contact_detail_nickname)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            label = { BcText(stringResource(Res.string.feature_contact_detail_contact)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveButtonClicked,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            BcText(
                stringResource(Res.string.feature_contact_detail_save),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
@Preview
fun ContactDetailPreview() {
    ContactDetailScreen(
        onBackPressed = {},
        onSaveButtonClicked = {}
    )
}