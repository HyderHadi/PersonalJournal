package xyz.hyderhadi.personaljournal.ui.appscreen

import androidx.room.TypeConverter
import java.util.Date


data class EntryCardUiState(
    val id: Int = 0,
    val title: String = "",
    val previewText: String = "",
    val date: String = "",
    val journalText: String = ""
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