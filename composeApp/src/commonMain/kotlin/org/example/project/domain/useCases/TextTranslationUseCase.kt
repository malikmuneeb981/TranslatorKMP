package org.example.project.domain.useCases

import org.example.project.domain.repos.TranslatorApiRepo

class TextTranslationUseCase(private val translatorApiRepo: TranslatorApiRepo) {

    suspend operator fun invoke(langFrom: String,
                                langTo: String,
                                textToTranslate: String): String = translatorApiRepo.getTranslatedText(langFrom = langFrom,
                                    langTo = langTo, textToTranslate = textToTranslate)

}