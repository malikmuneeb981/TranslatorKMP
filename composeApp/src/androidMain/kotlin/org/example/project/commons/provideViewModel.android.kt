package org.example.project.commons

import org.example.project.presentation.viewModels.TextTranslationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun provideViewModel(): Module {
    return module {
        viewModelOf(::TextTranslationViewModel)
    }
}