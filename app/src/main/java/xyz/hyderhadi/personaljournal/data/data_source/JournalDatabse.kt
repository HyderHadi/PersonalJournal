package xyz.hyderhadi.personaljournal.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry


@Database(entities = [JournalEntry::class], version = 1, exportSchema = false)
abstract class JournalDatabase: RoomDatabase() {


    abstract val journalEntryDao: JournalEntryDao

    companion object {
        const val DATABASE_NAME = "journal_db"
    }
}