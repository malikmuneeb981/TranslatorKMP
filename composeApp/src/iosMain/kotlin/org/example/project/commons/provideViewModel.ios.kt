package org.example.project.commons

import org.example.project.presentation.viewModels.TextTranslationViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun provideViewModel(): Module {
   return module {
       singleOf(::TextTranslationViewModel)
   }
}