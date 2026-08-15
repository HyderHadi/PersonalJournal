package xyz.hyderhadi.personaljournal.ui.entryscreen


sealed class  JournalEntryEvents {
    object SaveEntry: JournalEntryEvents()
    data class EnteredContent(val value: String): JournalEntryEvents()
    object UpdateEntry: JournalEntryEvents()
    object UpdateTitleForEntry: JournalEntryEvents()
}