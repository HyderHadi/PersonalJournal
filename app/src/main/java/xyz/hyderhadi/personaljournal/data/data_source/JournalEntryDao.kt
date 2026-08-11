package xyz.hyderhadi.personaljournal.data.data_source

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry

@Dao
interface JournalEntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(journalEntry: JournalEntry)

    @Update
    suspend fun update(journalEntry: JournalEntry)

    @Delete
    suspend fun delete(journalEntry: JournalEntry)

    @Query("SELECT * FROM entries WHERE id = :id")
    fun getJournalEntry(id: Int): Flow<JournalEntry>

    @Query("SELECT * FROM entries ORDER BY date DESC")
    fun getAllJournalEntries(): Flow<List<JournalEntry>>
}