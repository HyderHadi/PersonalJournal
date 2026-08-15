package xyz.hyderhadi.personaljournal

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import xyz.hyderhadi.personaljournal.ui.PersonalJournalAppBar
import xyz.hyderhadi.personaljournal.ui.appscreen.MainScreen
import xyz.hyderhadi.personaljournal.ui.entryscreen.JournalEntryScreen
import xyz.hyderhadi.personaljournal.ui.theme.PersonalJournalTheme
import xyz.hyderhadi.personaljournal.ui.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalJournalTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.JournalEntriesScreen.route
                ) {
                    composable(route = Screen.JournalEntriesScreen.route) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                PersonalJournalAppBar(
                                    canNavigateBack = navController.previousBackStackEntry != null,
                                    navigateUp = { navController.navigateUp() },
                                    currentScreen = Screen.JournalEntriesScreen
                                )
                            },
                            contentWindowInsets = WindowInsets(0)
                        ) {
                            MainScreen(navController = navController, modifier = Modifier.padding(it))
                        }
                    }

                    composable(
                        route = Screen.EntryScreen.route +
                            "?journalId={journalId}",
                        arguments = listOf(
                            navArgument(
                                name = "journalId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                PersonalJournalAppBar(
                                    canNavigateBack = navController.previousBackStackEntry != null,
                                    navigateUp = { navController.navigateUp() },
                                    currentScreen = Screen.EntryScreen
                                )
                            },
                            contentWindowInsets = WindowInsets(0)
                        ) {
                            JournalEntryScreen(navController = navController, modifier = Modifier.padding(it))
                        }
                    }
                }

            }
        }
    }
}