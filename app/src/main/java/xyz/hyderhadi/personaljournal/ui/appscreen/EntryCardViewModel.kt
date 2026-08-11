package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import xyz.hyderhadi.personaljournal.data.repository.OfflineEntriesRepositoryImpl


class EntryCardViewModel(
    private val offlineEntriesRepository: OfflineEntriesRepositoryImpl
): ViewModel() {

    private var _entryCardUiState = MutableStateFlow<List<EntryCardUiState>>(emptyList())
    val entryCardUiState: StateFlow<List<EntryCardUiState>> = _entryCardUiState.asStateFlow()

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

        fun openEntry(

        ) {
            // TODO
        }

        fun createEntry(

        ) {
            // TODO
        }

        fun deleteEntry(

        ) {
            // TODO
        }
    }
}