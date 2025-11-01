package org.example.project.domain.repos

interface TranslatorApiRepo {

    suspend fun getTranslatedText( langFrom: String,
                                   langTo: String,
                                   textToTranslate: String): String
}