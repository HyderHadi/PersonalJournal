package xyz.hyderhadi.personaljournal.ui.entryscreen

import androidx.compose.ui.focus.FocusState


sealed class  JournalEntryEvents {
    object SaveEntry: JournalEntryEvents()
    data class EnteredContent(val value: String): JournalEntryEvents()
    object UpdateEntry: JournalEntryEvents()
}