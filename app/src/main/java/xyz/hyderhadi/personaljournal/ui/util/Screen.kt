package xyz.hyderhadi.personaljournal.ui.util

sealed class Screen(
    val route: String
) {
    object JournalEntriesScreen: Screen("main_screen")
    object EntryScreen: Screen("entry_screen")
}