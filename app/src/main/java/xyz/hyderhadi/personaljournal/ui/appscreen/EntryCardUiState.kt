package xyz.hyderhadi.personaljournal.ui.appscreen



data class EntryCardUiState(
    val title: String = "",
    val date: String = "",
    val previewText: String = ""
)


val experimentalList: List<EntryCardUiState> = listOf<EntryCardUiState>(
    EntryCardUiState(
        "this is whatever",
        "Jan 7",
        "this is whatever kinda of note"
    ),
    EntryCardUiState(
        "this is another",
        "Aug 4",
        "this is another note holy moly"
    ),
    EntryCardUiState(
        "i want to say",
        "Oct 10",
        "i want to say happy bd nameer"
    )
)