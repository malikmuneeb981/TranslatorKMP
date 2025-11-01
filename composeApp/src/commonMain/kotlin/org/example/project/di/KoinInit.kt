package org.example.project.di

import androidx.compose.runtime.Composable
import org.example.project.commons.TranslationUtils
import org.example.project.commons.provideViewModel
import org.example.project.presentation.navigation.NavGraph
import org.koin.compose.KoinApplication


@Composable
fun KoinInit(translationUtils: TranslationUtils) {

    KoinApplication(application = {
        modules(koinModule, provideViewModel())
    }){
        NavGraph(translationUtils)
    }

}