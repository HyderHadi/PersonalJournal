package xyz.hyderhadi.personaljournal.ui.appscreen

import xyz.hyderhadi.personaljournal.domain.model.JournalEntry

sealed class EntryCardEvents {
    data class DeleteEntry(val journalEntry: JournalEntry): EntryCardEvents()
}