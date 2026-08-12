package xyz.hyderhadi.personaljournal.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val journalText: String,
    val modifiedAt: Long = System.currentTimeMillis(),
    val scrollState: Int = 0
)