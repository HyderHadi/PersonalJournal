package xyz.hyderhadi.personaljournal.ui.entryscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class JournalEntryViewModel: ViewModel() {

    private var _journalEntryUiState = MutableStateFlow(JournalEntryUiState())
    val journalEntryUiState: StateFlow<JournalEntryUiState> = _journalEntryUiState.asStateFlow()


    fun onEvents(journalEntryEvent: JournalEntryEvents) {

        when(journalEntryEvent) {

            JournalEntryEvents.SaveEntry -> {
                // TODO: implement a save function for an entry
                saveEntry("")
            }
        }
    }


    fun saveEntry(
        textInput: String
    ) {
        // TODO
    }
}