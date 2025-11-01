package org.example.project.data.apiServices

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText

class TranslatorApiService(private val client: HttpClient) {
    suspend fun getTranslatedText(
        langFrom: String,
        langTo: String,
        textToTranslate: String
    ): String {
        val baseUrl = "https://translate.googleapis.com"

        return try {
            val response = client.post("$baseUrl/translate_a/single") {
                url {
                    parameters.append("client", "gtx")
                    parameters.append("sl", langFrom)
                    parameters.append("tl", langTo)
                    parameters.append("dt", "t")
                    parameters.append("q", textToTranslate)
                }
            }

            if (response.status.value in 200..299) {
                response.bodyAsText()
            } else {
                "Error: ${response.status}"
            }
        } catch (e: Exception) {
            "Error: ${e}"
        }
    }

}