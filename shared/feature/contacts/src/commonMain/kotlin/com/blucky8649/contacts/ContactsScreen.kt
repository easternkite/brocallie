package com.blucky8649.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import brocallie.shared.feature.contacts.generated.resources.Res
import brocallie.shared.feature.contacts.generated.resources.feature_contact_title
import brocallie.shared.feature.contacts.generated.resources.message_callie_deleted
import brocallie.shared.feature.contacts.generated.resources.undo
import com.blucky8649.designsystem.BcTopAppBar
import com.blucky8649.room.BrocallieDatabase
import com.blucky8649.room.model.CallieEntity
import compose.icons.TablerIcons
import compose.icons.tablericons.UserPlus
import kotlinx.coroutines.launch
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
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BcTopAppBar(
                title = stringResource(Res.string.feature_contact_title),
//                actionIcon = Icons.Default.MoreVert
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddButtonClick) {
                Icon(
                    imageVector = TablerIcons.UserPlus,
                    contentDescription = "add callie"
                )
            }
        }
    ) { paddingValues ->
        val messageUndo = stringResource(Res.string.undo)
        val messageRemoved = stringResource(Res.string.message_callie_deleted)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .then(modifier)
        ) {
            items(uiState.contacts) { entity ->
                ContactItem(
                    contact = entity,
                    onRemoveCallie = {
                        scope.launch {
                            println("removed")
                            dbInject.callieDao().deleteCallie(it)
                            val result = snackbarHostState.showSnackbar(
                                message = messageRemoved,
                                actionLabel = messageUndo,
                            )
                            when(result) {
                                SnackbarResult.ActionPerformed -> {
                                    println("undo")
                                    dbInject.callieDao().insertCallie(entity)
                                }
                                else -> {}
                            }
                        }
                    },
                    onClick = onContactClick
                )
            }
        }
    }
}
