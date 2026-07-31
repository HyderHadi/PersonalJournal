package xyz.hyderhadi.personaljournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import xyz.hyderhadi.personaljournal.ui.entryscreen.JournalEntryScreen
import xyz.hyderhadi.personaljournal.ui.theme.PersonalJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalJournalTheme {
                Surface {
                    JournalEntryScreen()
                }
            }
        }
    }
}