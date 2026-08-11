package xyz.hyderhadi.personaljournal.domain.use_case

import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.domain.repository.EntriesRepository


class AddEntryUseCase(
    private val repository: EntriesRepository
) {
    suspend operator fun invoke(journalEntry: JournalEntry) {
        repository.createJournalEntry(journalEntry)
    }
}