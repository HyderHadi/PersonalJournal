package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.room.TypeConverter
import xyz.hyderhadi.personaljournal.domain.model.JournalEntry
import java.util.Date


data class EntryCardsUiState(
    val entries: List<JournalEntry> = emptyList(),
)

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}