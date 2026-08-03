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
import androidx.compose.ui.unit.dp
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
                    },
                    contentWindowInsets = WindowInsets(0)
                ) {
                    MainScreen(modifier = Modifier.padding(it), entryCards = experimentalList)
                }
            }
        }
    }
}