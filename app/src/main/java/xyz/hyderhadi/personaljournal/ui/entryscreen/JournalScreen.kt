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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import xyz.hyderhadi.personaljournal.R
import xyz.hyderhadi.personaljournal.ui.theme.AppTypography
import xyz.hyderhadi.personaljournal.ui.theme.Shapes





@Composable
fun JournalEntryScreen(
    modifier: Modifier = Modifier,
    viewModel: JournalEntryViewModel = hiltViewModel(),
    navController: NavController
) {
    // to get the specific colors for my theme
    val colors = MaterialTheme.colorScheme
    val state = viewModel.state.value
    var isDisabledFAB = true

    LaunchedEffect(
        key1 = true
    ) {
        viewModel.eventFlow.collectLatest { uiEvent ->
            when(uiEvent) {
                JournalEntryViewModel.UiEvent.SaveEntry -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        Column {

            Box(
                modifier = Modifier.fillMaxSize().background(colors.secondaryContainer)
            ) {

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .verticalScroll(state.scrollState)
                        .imePadding(),
                    textStyle = AppTypography.bodyLarge.copy(
                        color =  colors.onPrimary
                    ),
                    state = state.textFieldState,
                    cursorBrush = SolidColor(colors.onPrimaryContainer),
                    decorator = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            if(state.textFieldState.text.isEmpty()) {
                                Text(
                                    text = stringResource(R.string.place_holder_journal_entry),
                                    color = colors.onSecondary
                                )
                            }

                            innerTextField()
                        }
                    }
                )

                if(state.scrollState.maxValue > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .width(3.dp)
                            .background(colors.surfaceContainerLowest.copy(alpha = 0.3f))
                    )

                    val progress =
                        state.scrollState.value.toFloat() / state.scrollState.maxValue.toFloat()

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

                if (!state.textFieldState.text.toString().isBlank()) {
                    isDisabledFAB = false
                }
                FloatingActionButton (
                    onClick = { if(!isDisabledFAB) {
                        if(viewModel.currentJournalId != null) {
                            viewModel.onEvents(JournalEntryEvents.UpdateEntry)
                        } else {
                            viewModel.onEvents(JournalEntryEvents.SaveEntry)
                        }
                    }
                              },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(42.dp)
                        .size(72.dp),
                    containerColor = if(isDisabledFAB) {
                        colors.surfaceContainer
                    } else {
                        colors.primaryContainer
                    },
                    contentColor = if(isDisabledFAB) {
                        colors.surfaceBright
                    } else {
                        colors.onPrimaryContainer
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = stringResource(R.string.save_button_journal_screen)
                    )
                }

            }
        }

    }

}