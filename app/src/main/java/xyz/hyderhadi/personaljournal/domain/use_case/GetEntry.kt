package xyz.hyderhadi.personaljournal.domain.use_case

import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.domain.repository.EntriesRepository

class GetEntry(
    private val repository: EntriesRepository
) {
    suspend operator fun invoke(id: Int): JournalEntry {
        return repository.getEntry(id)
    }
}