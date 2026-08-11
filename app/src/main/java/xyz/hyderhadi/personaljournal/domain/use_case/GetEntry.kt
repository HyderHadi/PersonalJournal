package xyz.hyderhadi.personaljournal.domain.use_case

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.domain.repository.EntriesRepository

class GetEntry(
    private val repository: EntriesRepository
) {
    operator fun invoke(id: Int): Flow<JournalEntry> {
        return repository.getEntryStream(id)
    }
}