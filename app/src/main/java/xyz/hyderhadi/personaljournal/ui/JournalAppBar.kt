package xyz.hyderhadi.personaljournal.ui

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import xyz.hyderhadi.personaljournal.R
import xyz.hyderhadi.personaljournal.ui.entryscreen.JournalEntryEvents
import xyz.hyderhadi.personaljournal.ui.entryscreen.JournalEntryViewModel
import xyz.hyderhadi.personaljournal.ui.theme.bodyFontFamily
import xyz.hyderhadi.personaljournal.ui.util.Screen
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalJournalAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    currentScreen: Screen,
    journalEntryViewModel: JournalEntryViewModel = hiltViewModel(),
) {

    var expanded by remember { mutableStateOf(false) }
    var showEditTitleDialog by remember { mutableStateOf(false) }
    var editTitleTextField by remember { mutableStateOf("") }
    var editTitleTextFieldIsWhiteSpaceOnly by remember { mutableStateOf(false) }
    val context = LocalContext.current
    CenterAlignedTopAppBar(

        // TODO: maybe get different ASCII smiles each time the user wants an entry idk...
        title = { Text(stringResource(R.string.app_name),
                    textAlign = TextAlign.Center)
            },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ),
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(
                    onClick = {
                        navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_app_bar)
                    )
                }
            }
        },
        actions = {
            if(currentScreen == Screen.EntryScreen) {
                IconButton(
                    onClick = { expanded = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.more_options_content_description)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    shape = RoundedCornerShape(16.dp),
                    tonalElevation = 4.dp,
                    shadowElevation = 8.dp,
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(R.string.edit_title),
                                fontFamily = bodyFontFamily,
                            )
                        },
                        onClick = {
                                showEditTitleDialog = true
                                expanded = false
                        },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                    )

                    DropdownMenuItem(
                        text = {
                            Text("About")
                        },
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                "https://github.com/HyderHadi".toUri()
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                    )
                }

                if(showEditTitleDialog) {
                    AlertDialog(
                        onDismissRequest = { },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    if (!editTitleTextFieldIsWhiteSpaceOnly && editTitleTextField.isNotEmpty()) {
                                        showEditTitleDialog = false
                                        journalEntryViewModel.updateEntryTitleUiState(text = editTitleTextField)
                                        journalEntryViewModel.onEvents(JournalEntryEvents.UpdateTitleForEntry)
                                    }
                                }
                            ) {
                                Text("Save")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { showEditTitleDialog = false }
                            ) {
                                Text("Cancel")
                            }
                        },
                        title = {
                            Text("Edit Title")
                        },
                        text = {
                            OutlinedTextField(
                                value = editTitleTextField,
                                onValueChange = {
                                    if(it.length < 50) {
                                        editTitleTextField = it
                                    }
                                },
                                label = {
                                    Text(if(editTitleTextField.isNotEmpty() && editTitleTextField.all { it.isWhitespace() }) {
                                        editTitleTextFieldIsWhiteSpaceOnly = true
                                        "Blank names are not allowed"
                                    } else {
                                        editTitleTextFieldIsWhiteSpaceOnly = false
                                        "Title"
                                    })
                                },
                                singleLine = true,
                                isError = editTitleTextFieldIsWhiteSpaceOnly,
                                colors = OutlinedTextFieldDefaults.colors(
                                    errorLabelColor = MaterialTheme.colorScheme.error,
                                    errorBorderColor = MaterialTheme.colorScheme.errorContainer
                                )
                            )
                        }
                    )
                }
            }
        }
    )
}