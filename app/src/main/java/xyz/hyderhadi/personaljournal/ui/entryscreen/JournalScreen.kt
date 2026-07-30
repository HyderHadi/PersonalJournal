package xyz.hyderhadi.personaljournal.ui.entryscreen

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.hyderhadi.personaljournal.ui.theme.AppTypography


@Composable
fun JournalEntryScreen(

    modifier: Modifier = Modifier
) {
    // for initial testing of the entryScreen
    var text by rememberSaveable { mutableStateOf("") }

    val colors = MaterialTheme.colorScheme

    Box(
        modifier = modifier.statusBarsPadding()
    ) {
        Column {

            Box(
                modifier = Modifier.fillMaxSize().background(colors.secondaryContainer),

            ) {

                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    textStyle = AppTypography.bodyLarge.copy(
                        color =  colors.onPrimary
                    ),
                    cursorBrush = SolidColor(colors.onPrimaryContainer),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            if(text.isEmpty()) {
                                Text("Type Your Entry ...",
                                    color = colors.onSecondary
                                    )
                            }

                            innerTextField()
                        }
                    }
                )
            }
        }

    }

}


@Preview
@Composable
fun JournalEntryScreenPreview() {

    JournalEntryScreen()

}