package xyz.hyderhadi.personaljournal.domain.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry

interface EntriesRepository {

    fun getAllEntriesStream(): Flow<List<JournalEntry>>

    suspend fun getEntry(id: Int): JournalEntry

    suspend fun createJournalEntry(journalEntry: JournalEntry)

    suspend fun deleteJournalEntry(journalEntry: JournalEntry)

    suspend fun updateJournalEntry(journalEntry: JournalEntry)
}