package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.hyderhadi.personaljournal.ui.theme.Shapes


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
                modifier = Modifier.fillMaxSize().background(colors.background).padding(8.dp)
            ) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(entryCards) { entry ->

                        EntryCard(entryCardUiState = entry)
                    }
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
            modifier = Modifier.fillMaxSize().padding(4.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.clip(Shapes.medium)
            ) {

                Text(
                    text = entryCardUiState.date
                )
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