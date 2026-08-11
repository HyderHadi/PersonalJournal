package xyz.hyderhadi.personaljournal.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.hyderhadi.personaljournal.data.data_source.JournalDatabase
import xyz.hyderhadi.personaljournal.data.repository.OfflineEntriesRepositoryImpl
import xyz.hyderhadi.personaljournal.domain.repository.EntriesRepository
import xyz.hyderhadi.personaljournal.domain.use_case.AddEntryUseCase
import xyz.hyderhadi.personaljournal.domain.use_case.DeleteEntryUseCase
import xyz.hyderhadi.personaljournal.domain.use_case.GetAllEntriesUseCase
import xyz.hyderhadi.personaljournal.domain.use_case.GetEntry
import xyz.hyderhadi.personaljournal.domain.use_case.JournalUseCases
import xyz.hyderhadi.personaljournal.domain.use_case.UpdateEntryUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJournalDatabase(app: Application): JournalDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = JournalDatabase::class.java,
            name = JournalDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideEntriesRepository(db: JournalDatabase): EntriesRepository {
        return OfflineEntriesRepositoryImpl(db.journalEntryDao)
    }

    @Provides
    @Singleton
    fun provideJournalUseCases(repository: EntriesRepository): JournalUseCases {
        return JournalUseCases(
            addEntryUseCase = AddEntryUseCase(repository),
            deleteEntryUseCase = DeleteEntryUseCase(repository),
            getAllEntriesUseCase = GetAllEntriesUseCase(repository),
            getEntry = GetEntry(repository),
            updateEntryUseCase = UpdateEntryUseCase(repository)
        )
    }
}