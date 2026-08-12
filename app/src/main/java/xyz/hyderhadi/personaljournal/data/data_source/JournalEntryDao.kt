package xyz.hyderhadi.personaljournal.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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
    suspend fun getJournalEntry(id: Int): JournalEntry

    @Query("SELECT * FROM entries ORDER BY modifiedAt DESC")
    fun getAllJournalEntries(): Flow<List<JournalEntry>>
}