package xyz.hyderhadi.personaljournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import xyz.hyderhadi.personaljournal.ui.PersonalJournalAppBar
import xyz.hyderhadi.personaljournal.ui.appscreen.MainScreen
import xyz.hyderhadi.personaljournal.ui.appscreen.experimentalList
import xyz.hyderhadi.personaljournal.ui.theme.PersonalJournalTheme

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
                    }
                ) { innerPadding ->

                    MainScreen(modifier = Modifier.padding(innerPadding), entryCards = experimentalList)
                }
            }
        }
    }
}