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
                    journalUseCases.getEntry(journalId).also {
                        currentJournalId = it.id
                        _state.value = state.value.copy(
                            textFieldState = TextFieldState(it.journalText),
                            scrollState = ScrollState(it.scrollState),
                            entryTitle = it.title,
                            oldTextFieldState = TextFieldState(it.journalText)
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
                            if (state.value.entryTitle != "") {
                                journalUseCases.addEntryUseCase(
                                    JournalEntry(
                                        title = state.value.entryTitle,
                                        journalText = state.value.textFieldState.text.toString(),
                                        scrollState = state.value.scrollState.value
                                    )
                                )
                            }
                            else {
                                journalUseCases.addEntryUseCase(
                                    JournalEntry(
                                        title = state.value.textFieldState.text.toString(),
                                        journalText = state.value.textFieldState.text.toString(),
                                        scrollState = state.value.scrollState.value
                                    )
                                )
                            }
                            _eventFlow.emit(UiEvent.SaveEntry)
                        }
                    }
                }
            is JournalEntryEvents.EnteredContent -> {
                // IDK
            }
            is JournalEntryEvents.UpdateEntry -> {
                if(currentJournalId != null) {
                    viewModelScope.launch {
                        if(state.value.entryTitle != "") {
                            journalUseCases.updateEntryUseCase(
                                JournalEntry(
                                    id = currentJournalId!!,
                                    title = state.value.entryTitle,
                                    journalText = state.value.textFieldState.text.toString(),
                                    scrollState = state.value.scrollState.value
                                )
                            )
                        }
                        else {
                            journalUseCases.updateEntryUseCase(
                                JournalEntry(
                                    id = currentJournalId!!,
                                    title = state.value.textFieldState.text.toString(),
                                    journalText = state.value.textFieldState.text.toString(),
                                    scrollState = state.value.scrollState.value
                                )
                            )
                        }
                        _eventFlow.emit(UiEvent.UpdateEntry)
                    }
                }
            }
            is JournalEntryEvents.UpdateTitleForEntry -> {
                if(currentJournalId != null) {
                    viewModelScope.launch {
                        journalUseCases.updateEntryUseCase(
                            JournalEntry(
                                id = currentJournalId!!,
                                title = state.value.entryTitle,
                                journalText = journalUseCases.getEntry(currentJournalId!!).journalText,
                                scrollState = state.value.scrollState.value
                            )
                        )
                        _eventFlow.emit(UiEvent.UpdateTitleOFEntry)
                    }
                }
            }
        }
    }

    fun updateEntryTitleUiState(text: String) {
        _state.value = state.value.copy(
            entryTitle = text
        )
    }

    fun loadTutorialIntoTextFieldUiState(text: String) {
        _state.value = state.value.copy(
            textFieldState = TextFieldState(text)
        )
    }


    sealed class UiEvent {
        object SaveEntry: UiEvent()
        object UpdateEntry: UiEvent()
        object UpdateTitleOFEntry: UiEvent()
    }
}