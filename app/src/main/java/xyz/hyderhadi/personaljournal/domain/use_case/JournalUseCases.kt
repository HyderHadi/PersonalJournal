package xyz.hyderhadi.personaljournal.domain.use_case

data class JournalUseCases(
    val addEntryUseCase: AddEntryUseCase,
    val deleteEntryUseCase: DeleteEntryUseCase,
    val getAllEntriesUseCase: GetAllEntriesUseCase,
    val getEntry: GetEntry,
    val updateEntryUseCase: UpdateEntryUseCase
)