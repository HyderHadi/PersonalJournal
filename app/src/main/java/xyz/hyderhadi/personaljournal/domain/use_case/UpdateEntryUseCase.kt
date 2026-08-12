package xyz.hyderhadi.personaljournal.domain.use_case

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.domain.repository.EntriesRepository

class UpdateEntryUseCase(
    private val repository: EntriesRepository
) {
    suspend operator fun invoke(journalEntry: JournalEntry) {
        repository.updateJournalEntry(journalEntry)
    }
}