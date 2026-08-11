package xyz.hyderhadi.personaljournal.data.data_source

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room3.Database
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import xyz.hyderhadi.personaljournal.ui.appscreen.Converters


@Database(entities = [JournalEntry::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class JournalDatabase: RoomDatabase() {


    abstract val journalEntryDao: JournalEntryDao

    companion object {
        const val DATABASE_NAME = "journal_db"
    }
}