package xyz.hyderhadi.personaljournal.data.repository

import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.personaljournal.domain.repository.EntriesRepository
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.data.data_source.JournalEntryDao

class OfflineEntriesRepositoryImpl(private val journalEntryDao: JournalEntryDao): EntriesRepository {


    override fun getAllEntriesStream(): Flow<List<JournalEntry>> = journalEntryDao.getAllJournalEntries()

    override fun getEntryStream(id: Int): Flow<JournalEntry> = journalEntryDao.getJournalEntry(id)

    override suspend fun createJournalEntry(journalEntry: JournalEntry) = journalEntryDao.insert(journalEntry)

    override suspend fun deleteJournalEntry(journalEntry: JournalEntry) = journalEntryDao.delete(journalEntry)

    override suspend fun updateJournalEntry(journalEntry: JournalEntry) = journalEntryDao.update(journalEntry)
}