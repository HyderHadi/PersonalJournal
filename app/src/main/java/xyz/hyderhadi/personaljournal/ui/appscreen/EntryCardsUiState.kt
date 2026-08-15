package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.room.TypeConverter
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import java.util.Date


data class EntryCardsUiState(
    val entries: List<JournalEntry> = emptyList(),
)