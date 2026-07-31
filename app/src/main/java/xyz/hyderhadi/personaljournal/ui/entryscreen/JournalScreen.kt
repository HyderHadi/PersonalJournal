package xyz.hyderhadi.personaljournal.ui.entryscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.hyderhadi.personaljournal.ui.theme.AppTypography
import xyz.hyderhadi.personaljournal.ui.theme.Shapes


@Composable
fun JournalEntryScreen(

    modifier: Modifier = Modifier
) {
    // for initial testing of the entryScreen
    val state = rememberTextFieldState()
    val colors = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier.statusBarsPadding()
    ) {
        Column {

            Box(
                modifier = Modifier.fillMaxSize().background(colors.secondaryContainer)
            ) {

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .verticalScroll(scrollState)
                        .imePadding(),
                    textStyle = AppTypography.bodyLarge.copy(
                        color =  colors.onPrimary
                    ),
                    state = state,
                    cursorBrush = SolidColor(colors.onPrimaryContainer),
                    decorator = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            if(state.text.isEmpty()) {
                                Text("Type Your Entry ...",
                                    color = colors.onSecondary
                                )
                            }

                            innerTextField()
                        }
                    }
                )

                if(scrollState.maxValue > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(colors.surfaceContainerLowest.copy(alpha = 0.3f))
                    )

                    val progress =
                        scrollState.value.toFloat() / scrollState.maxValue.toFloat()

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(y = (progress * 300).dp)
                            .width(6.dp)
                            .height(48.dp)
                            .background(
                                colors.surfaceDim,
                                Shapes.small
                            )
                    )
                }

            }
        }

    }

}


@Preview
@Composable
fun JournalEntryScreenPreview() {

    JournalEntryScreen()

}