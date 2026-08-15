package xyz.hyderhadi.personaljournal.ui.appscreen

import xyz.hyderhadi.personaljournal.domain.model.JournalEntry


data class EntryCardsUiState(
    val entries: List<JournalEntry> = emptyList(),
)