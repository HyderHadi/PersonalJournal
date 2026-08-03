package xyz.hyderhadi.personaljournal.ui.appscreen

sealed interface EntryCardEvents {
    object CreateEntry: EntryCardEvents
    object OpenEntry: EntryCardEvents
    object DeleteEntry: EntryCardEvents
}