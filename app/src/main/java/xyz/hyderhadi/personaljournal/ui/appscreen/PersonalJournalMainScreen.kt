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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import xyz.hyderhadi.personaljournal.R
import xyz.hyderhadi.personaljournal.ui.entryscreen.JournalEntryEvents
import xyz.hyderhadi.personaljournal.ui.theme.AppTypography
import xyz.hyderhadi.personaljournal.ui.theme.Shapes
import xyz.hyderhadi.personaljournal.ui.theme.bodyFontFamily
import xyz.hyderhadi.personaljournal.ui.theme.displayFontFamily


@Composable
fun MainScreen(
    entryCards: List<EntryCardUiState>,
    modifier: Modifier = Modifier
) {

    val colors = MaterialTheme.colorScheme

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
                    items(entryCards){ entry ->
                        EntryCard(entryCardUiState = entry)
                    }
                }

                FloatingActionButton(
                    onClick = {  },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(36.dp)
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

    MainScreen(
        entryCards = experimentalList
    )
}