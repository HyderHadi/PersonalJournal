package xyz.hyderhadi.personaljournal.ui.appscreen

import android.os.Build
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import xyz.hyderhadi.personaljournal.R
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.ui.theme.Shapes
import xyz.hyderhadi.personaljournal.ui.theme.bodyFontFamily
import xyz.hyderhadi.personaljournal.ui.theme.displayFontFamily
import xyz.hyderhadi.personaljournal.ui.util.Screen
import xyz.hyderhadi.personaljournal.ui.util.formatTimeStamp


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: EntryCardsViewModel = hiltViewModel()
) {

    val colors = MaterialTheme.colorScheme
    val state = viewModel.state.value
    // val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
                    .padding(8.dp)
            ) {

                if(state.entries.isEmpty()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "No Journal Entries Yet\n\n(｡•́︿•̀｡)っ",
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontFamily = bodyFontFamily
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Press + to start an entry",
                            fontFamily = bodyFontFamily,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                else {
                    LazyColumn {
                        items(state.entries){ entry ->
                            EntryCard(
                                entry = entry,
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(
                                            Screen.EntryScreen.route +
                                                    "?journalId=${entry.id}"
                                        )
                                    },
                                onDeleteClick = { viewModel.onEvent(EntryCardEvents.DeleteEntry(entry)) }
                            )
                        }
                    }
                }

                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.EntryScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontal = 36.dp, vertical = 78.dp)
                        .size(64.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.save_button_journal_screen)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EntryCard(
    entry: JournalEntry,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {

    var showAlertDialog by remember { mutableStateOf(false) }


    Box(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.clip(Shapes.medium)
            ) {

                Row(
                    modifier = Modifier.padding(8.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .height(56.dp)
                            .weight(1f)
                    ) {

                        Text(
                            text = entry.title,
                            fontFamily = displayFontFamily,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = entry.journalText,
                            fontSize = 10.sp,
                            fontFamily = bodyFontFamily,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier
                            .height(56.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = formatTimeStamp(entry.modifiedAt),
                            fontFamily = displayFontFamily,
                            fontSize = 14.sp,
                        )

                        IconButton(
                            onClick = { showAlertDialog = true },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                tint = MaterialTheme.colorScheme.onSurface,
                                contentDescription = "Delete Icon"
                            )
                        }

                        if(showAlertDialog) {
                            AlertDialog(
                                onDismissRequest = { },
                                confirmButton = {
                                    TextButton(
                                        onClick = {
                                            showAlertDialog = false
                                            onDeleteClick()
                                        }
                                    ) {
                                        Text("Delete")
                                    }
                                },
                                dismissButton = {
                                    TextButton(
                                        onClick = { showAlertDialog = false }
                                    ) {
                                        Text("Cancel")
                                    }
                                },
                                title = {
                                    Text("Delete Journal Entry?")
                                }
                            )
                        }
                    }

                }


            }
        }
    }
}