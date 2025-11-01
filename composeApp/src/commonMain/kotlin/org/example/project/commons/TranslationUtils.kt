package org.example.project.commons

expect class TranslationUtils{
     fun copyToClipboard(text:String)
     fun startTTS(text: String)
     fun stopTTS()
     fun shareText(text: String, title: String = "")
}