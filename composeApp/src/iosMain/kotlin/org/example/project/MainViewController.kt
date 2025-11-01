package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.commons.TranslationUtils
import org.example.project.di.KoinInit

fun MainViewController() = ComposeUIViewController {
    val translationUtils = TranslationUtils()
    KoinInit(translationUtils) }