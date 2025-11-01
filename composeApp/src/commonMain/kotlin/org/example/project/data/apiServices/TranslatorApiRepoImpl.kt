package org.example.project.data.apiServices

import org.example.project.domain.repos.TranslatorApiRepo

class TranslatorApiRepoImpl(private val translatorApiService: TranslatorApiService): TranslatorApiRepo {
    override suspend fun getTranslatedText(
        langFrom: String,
        langTo: String,
        textToTranslate: String
    ): String {
        return translatorApiService.getTranslatedText(langFrom = langFrom,
            langTo=langTo,textToTranslate=textToTranslate)
    }
}