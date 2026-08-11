package xyz.hyderhadi.personaljournal.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.domain.repository.EntriesRepository

class GetAllEntriesUseCase(
    private val repository: EntriesRepository
) {
    operator fun invoke(): Flow<List<JournalEntry>> {
        return repository.getAllEntriesStream().map { entries ->
            entries.sortedByDescending { it.timeStamp }
        }
    }
}