package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import xyz.hyderhadi.personaljournal.domain.use_case.JournalUseCases
import javax.inject.Inject


@HiltViewModel
class EntryCardsViewModel @Inject constructor(
    private val journalUseCases: JournalUseCases
): ViewModel() {


    private val _state = mutableStateOf(EntryCardsUiState())
    val state = _state

    init {
        getJournalEntries()
    }

    fun onEvent(entryCardEvent: EntryCardEvents) {

        when(entryCardEvent) {
            is EntryCardEvents.DeleteEntry -> {
                viewModelScope.launch {
                    journalUseCases.deleteEntryUseCase(entryCardEvent.journalEntry)
                }
            }
        }
    }

    private fun getJournalEntries() {
        journalUseCases.getAllEntriesUseCase()
            .onEach { entries ->
                _state.value = state.value.copy(
                    entries = entries
                )
            }
            .launchIn(viewModelScope)
    }
}