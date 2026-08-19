package xyz.hyderhadi.personaljournal.ui.entryscreen

import android.R.attr.font
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mikepenz.markdown.coil3.Coil3ImageTransformerImpl
import com.mikepenz.markdown.compose.LazyMarkdownSuccess
import kotlinx.coroutines.flow.collectLatest
import xyz.hyderhadi.personaljournal.R
import xyz.hyderhadi.personaljournal.ui.theme.AppTypography
import xyz.hyderhadi.personaljournal.ui.theme.Shapes
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownColor
import com.mikepenz.markdown.m3.markdownTypography
import com.mikepenz.markdown.model.MarkdownAnimations
import com.mikepenz.markdown.model.MarkdownTypography
import com.mikepenz.markdown.model.markdownAnimations
import com.mikepenz.markdown.model.rememberMarkdownState


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
    var previewMarkdown by remember { mutableStateOf(false) }
    val markdownState = rememberMarkdownState(
        state.textFieldState.text.toString()
    )
    LaunchedEffect(
        key1 = true
    ) {
        viewModel.eventFlow.collectLatest { uiEvent ->
            when(uiEvent) {
                JournalEntryViewModel.UiEvent.SaveEntry -> {
                    navController.navigateUp()
                }

                JournalEntryViewModel.UiEvent.UpdateEntry -> {
                    navController.navigateUp()
                }

                JournalEntryViewModel.UiEvent.UpdateTitleOFEntry -> {

                }
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        Column {

            Box(
                modifier = Modifier.fillMaxSize().background(colors.secondaryContainer).imePadding()
            ) {

                if(previewMarkdown) {
                    Markdown(
                        markdownState = markdownState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        colors = markdownColor(
                            text = colors.onPrimary,
                            codeBackground = colors.tertiaryContainer,
                            inlineCodeBackground = colors.tertiary,
                        ),
                        imageTransformer = Coil3ImageTransformerImpl,
                        success = { markdownState, components, modifier ->
                            LazyMarkdownSuccess(
                                state = markdownState,
                                components = components,
                                modifier = modifier
                            )
                        },
                        animations = markdownAnimations(
                            animateTextSize = { this }
                        ),
                        typography = markdownTypography(

                        )
                    )
                }
                else {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxSize()
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
                                .offset(y = (progress * 800).dp)
                                .width(6.dp)
                                .height(48.dp)
                                .background(
                                    colors.surfaceDim,
                                    Shapes.small
                                )
                        )
                    }
                }


                if (!state.textFieldState.text.toString().isBlank()) {
                    isDisabledFAB = false
                }

                Row {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
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
                                .padding(horizontal = 36.dp, vertical = 78.dp)
                                .size(64.dp),
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

                        FloatingActionButton (
                            onClick = {
                                previewMarkdown = !previewMarkdown
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(horizontal = 110.dp, vertical = 78.dp)
                                .size(42.dp),
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
                            if(previewMarkdown && state.textFieldState.text.toString().isNotBlank()) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "Turn Off Preview MarkDown"
                                )
                            } else {

                                Icon(
                                    imageVector = Icons.Default.VisibilityOff,
                                    contentDescription = "Preview MarkDown"
                                )
                            }
                        }

                    }
                }
            }
        }

    }

}