package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class EntryCardViewModel: ViewModel() {

    private var _entryCardViewModel = MutableStateFlow(EntryCardUiState())
    val entryCardViewModel: StateFlow<EntryCardUiState> = _entryCardViewModel.asStateFlow()

    fun onEvents(
        entryCardEvent: EntryCardEvents
    ) {

        when(entryCardEvent) {

            EntryCardEvents.OpenEntry -> {
                // TODO
            }

            EntryCardEvents.CreateEntry -> {
                // TODO
            }

            EntryCardEvents.DeleteEntry -> {
                // TODO
            }
        }
    }
}