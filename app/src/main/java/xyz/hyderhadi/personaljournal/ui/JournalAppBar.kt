package xyz.hyderhadi.personaljournal.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import xyz.hyderhadi.personaljournal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalJournalAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
) {


    // to get the specific colors for my theme
    val colors = MaterialTheme.colorScheme

    CenterAlignedTopAppBar(

        // TODO: maybe get different ASCII smiles each time the user wants an entry idk...
        title = { Text(stringResource(R.string.app_name),
                    textAlign = TextAlign.Center)
            // TODO: The entry's name or first couple of words

            },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colors.inversePrimary
        ),
        modifier = modifier.fillMaxWidth(),
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(
                    onClick = navigateUp
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button_app_bar)
                    )
                }
            }
        }
    )
}