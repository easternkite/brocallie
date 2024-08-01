package com.blucky8649.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import brocallie.shared.feature.contacts.generated.resources.Res
import brocallie.shared.feature.contacts.generated.resources.feature_contact_title
import com.blucky8649.designsystem.BcTopAppBar
import com.blucky8649.room.BrocallieDatabase
import com.blucky8649.room.model.CallieEntity
import compose.icons.TablerIcons
import compose.icons.tablericons.UserPlus
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    onContactClick: (CallieEntity) -> Unit = {},
    onAddButtonClick: () -> Unit = {}
) {
    val dbInject = koinInject<BrocallieDatabase>()
    val viewModel: ContactViewModel = viewModel { ContactViewModel(dbInject) }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            BcTopAppBar(
                title = stringResource(Res.string.feature_contact_title),
                actionIcon = Icons.Default.MoreVert
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddButtonClick) {
                Icon(
                    imageVector = TablerIcons.UserPlus,
                    contentDescription = "add callie"
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .then(modifier)
        ) {
            items(uiState.contacts) {
                ContactItem(contact = it) {
                    onContactClick(it)
                    println("click ${it.name}")
                }
            }
        }
    }

}