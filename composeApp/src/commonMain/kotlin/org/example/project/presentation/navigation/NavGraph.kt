package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.commons.TranslationUtils
import org.example.project.presentation.screens.FromLangSelectionScreen
import org.example.project.presentation.screens.TextTranslationScreen
import org.example.project.presentation.screens.ToLangSelectionScreen
import org.example.project.presentation.viewModels.TextTranslationViewModel
import org.koin.compose.koinInject

@Composable
fun NavGraph(translationUtils: TranslationUtils) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navigation_Routes.TextTranslationScreen){
        composable(Navigation_Routes.TextTranslationScreen) {
            val textTranslationViewModel = koinInject<TextTranslationViewModel>()
            TextTranslationScreen(
                navController,
                textTranslationViewModel,
                translationUtils
            )
        }
        composable(Navigation_Routes.LangSelectionFrom) {
            val textTranslationViewModel = koinInject<TextTranslationViewModel>()
            FromLangSelectionScreen(
                navController  = navController
                //textTranslationViewModel
            )
        }

        composable(Navigation_Routes.LangSelectionTo) {
            val textTranslationViewModel = koinInject<TextTranslationViewModel>()
            ToLangSelectionScreen(
                navController  = navController
                //textTranslationViewModel
            )
        }

    }
}