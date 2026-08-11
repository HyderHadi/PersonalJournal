package xyz.hyderhadi.personaljournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import xyz.hyderhadi.personaljournal.data.data_source.JournalDatabase
import xyz.hyderhadi.personaljournal.data.repository.OfflineEntriesRepositoryImpl
import xyz.hyderhadi.personaljournal.ui.PersonalJournalAppBar
import xyz.hyderhadi.personaljournal.ui.appscreen.EntryCardViewModel
import xyz.hyderhadi.personaljournal.ui.appscreen.MainScreen
import xyz.hyderhadi.personaljournal.ui.theme.PersonalJournalTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalJournalTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        PersonalJournalAppBar()
                    },
                    contentWindowInsets = WindowInsets(0)
                ) {
                    MainScreen(modifier = Modifier.padding(it))
                }
            }
        }
    }
}