package xyz.hyderhadi.personaljournal.ui.entryscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.text.input.TextFieldState


data class JournalEntryUiState(
    val textFieldState: TextFieldState = TextFieldState(""),
    val scrollState: ScrollState = ScrollState(0),
    val entryTitle: String = "",
    val oldTextFieldState: TextFieldState = TextFieldState("")
)