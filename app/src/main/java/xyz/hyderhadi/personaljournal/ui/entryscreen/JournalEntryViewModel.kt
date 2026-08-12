package xyz.hyderhadi.personaljournal.ui.entryscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.domain.use_case.JournalUseCases
import javax.inject.Inject

@HiltViewModel
class JournalEntryViewModel @Inject constructor(
    private val journalUseCases: JournalUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(JournalEntryUiState())
    val state = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentJournalId: Int? = null
        private set

    init {
        savedStateHandle.get<Int>("journalId")?.let { journalId ->
            if(journalId != -1) {
                viewModelScope.launch {
                    journalUseCases.getEntry(journalId)?.also {
                        currentJournalId = it.id
                        _state.value = state.value.copy(
                            textFieldState = TextFieldState(it.journalText),
                            scrollState = ScrollState(it.scrollState)
                        )
                    }
                }
            }
        }
    }

    fun onEvents(journalEntryEvent: JournalEntryEvents) {
        when(journalEntryEvent) {
            is JournalEntryEvents.SaveEntry -> {
                    if(currentJournalId == null) {
                        viewModelScope.launch {
                            journalUseCases.addEntryUseCase(
                                JournalEntry(
                                    title = state.value.textFieldState.text.toString(),
                                    journalText = state.value.textFieldState.text.toString(),
                                    scrollState = state.value.scrollState.value
                                )
                            )
                            _eventFlow.emit(UiEvent.SaveEntry)
                        }
                    }
                }
            is JournalEntryEvents.EnteredContent -> {
                _state.value = _state.value.copy(
                    textFieldState = TextFieldState(journalEntryEvent.value)
                )
            }
            is JournalEntryEvents.UpdateEntry -> {
                if(currentJournalId != null) {
                    viewModelScope.launch {
                        journalUseCases.updateEntryUseCase(
                            JournalEntry(
                                id = currentJournalId!!,
                                title = state.value.textFieldState.text.toString(),
                                journalText = state.value.textFieldState.text.toString(),
                                scrollState = state.value.scrollState.value
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveEntry)
                    }
                }
            }
        }
    }
    sealed class UiEvent {
        object SaveEntry: UiEvent()
    }
}