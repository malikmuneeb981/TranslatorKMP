package org.example.project.di

import org.example.project.commons.getKtorClient
import org.example.project.data.apiServices.TranslatorApiRepoImpl
import org.example.project.data.apiServices.TranslatorApiService
import org.example.project.domain.repos.TranslatorApiRepo
import org.example.project.domain.useCases.TextTranslationUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val koinModule = module {

    single <TranslatorApiService>{ TranslatorApiService(getKtorClient()) }

    single <TranslatorApiRepo>{ TranslatorApiRepoImpl(get<TranslatorApiService>()) }

    singleOf(::TextTranslationUseCase)




}