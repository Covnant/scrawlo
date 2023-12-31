package com.covenant.noteapp


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.covenant.noteapp.screens.AddNoteScreen
import com.covenant.noteapp.screens.ArchivedScreen
import com.covenant.noteapp.screens.EditArchivedScreen
import com.covenant.noteapp.screens.EditNoteScreen
import com.covenant.noteapp.screens.NoteScreen
import com.covenant.noteapp.screens.sharedXAxisEnter
import com.covenant.noteapp.screens.sharedXAxisExit
import com.covenant.noteapp.viewmodel.NoteViewModel

@Composable
fun AppNavigation(viewModel: NoteViewModel) {
    val density = LocalDensity.current

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "noteScreen",
        enterTransition = { density.sharedXAxisEnter() },
        exitTransition = { density.sharedXAxisExit() },
        popEnterTransition = { density.sharedXAxisEnter(forward = false) },
        popExitTransition = { density.sharedXAxisExit(forward = false) },
    ) {
        composable("noteScreen") {
            NoteScreen(navController, viewModel)
        }
        composable("addNoteScreen") {
            AddNoteScreen(navController,viewModel)
        }
        composable("archivedScreen"){
            ArchivedScreen(navController, viewModel)
        }
        composable( route = "editArchivedScreen/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val noteId = arguments.getInt("noteId")
            EditArchivedScreen(navController, viewModel, noteId)
        }
        composable(
            route = "editNoteScreen/{noteId}",
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val noteId = arguments.getInt("noteId")
            EditNoteScreen(navController, viewModel, noteId)
        }
    }
}


