package org.example.project.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive
import org.example.project.domain.useCases.TextTranslationUseCase

class TextTranslationViewModel(private val textTranslationUseCase: TextTranslationUseCase) :
    ViewModel() {


    private val _translationResult = MutableStateFlow<String>("")
    val translationResult = _translationResult.asStateFlow()
    fun getTranslation(
        langFrom: String,
        langTo: String,
        textToTranslate: String
    ) = viewModelScope.launch {

        val result = extractTranslation(
            textTranslationUseCase.invoke(
                langFrom = langFrom,
                langTo = langTo, textToTranslate = textToTranslate
            )
        )

        _translationResult.emit(result)
        print("Translation Result , $result")


    }

    fun extractTranslation(response: String): String {
        return try {
            val json = Json.parseToJsonElement(response)
            // Navigate into: [0][0][0]
            json.jsonArray[0]
                .jsonArray[0]
                .jsonArray[0]
                .jsonPrimitive
                .content
        } catch (e: Exception) {
            "Error parsing translation"
        }
    }

}