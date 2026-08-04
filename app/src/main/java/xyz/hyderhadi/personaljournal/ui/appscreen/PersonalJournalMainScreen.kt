package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import xyz.hyderhadi.personaljournal.R
import xyz.hyderhadi.personaljournal.ui.theme.Shapes
import xyz.hyderhadi.personaljournal.ui.theme.bodyFontFamily
import xyz.hyderhadi.personaljournal.ui.theme.displayFontFamily


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    entryCardViewModel: EntryCardViewModel = viewModel()
) {

    val colors = MaterialTheme.colorScheme
    // collect the flow state later
    val entryCardUiState = experimentalList

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

                LazyColumn {
                    items(entryCardUiState){ entry ->
                        EntryCard(entryCardUiState = entry)
                    }
                }

                FloatingActionButton(
                    onClick = { entryCardViewModel.onEvents(EntryCardEvents.CreateEntry) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(36.dp)
                        .size(72.dp)
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


@Composable
fun EntryCard(
    entryCardUiState: EntryCardUiState,
    modifier: Modifier = Modifier
) {


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
                            text = entryCardUiState.title,
                            fontFamily = displayFontFamily,
                            fontSize = 14.sp
                        )
                        Text(
                            text = entryCardUiState.previewText,
                            fontSize = 12.sp,
                            fontFamily = bodyFontFamily,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                    }

                    Text(
                        text = entryCardUiState.date,
                        fontFamily = displayFontFamily,
                        fontSize = 14.sp,
                    )
                }


            }
        }
    }
}



@Preview (showBackground = true)
@Composable
fun CardPreview() {

    MainScreen()
}