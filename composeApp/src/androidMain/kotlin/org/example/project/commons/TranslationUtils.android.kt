package org.example.project.commons

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import java.util.Locale

actual class TranslationUtils(val context: Context) {
    actual fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
    }
    private var tts: TextToSpeech? = null

    actual fun startTTS(text: String) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
            }
        }
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    actual fun stopTTS() {
        tts?.stop()
        tts?.shutdown()
    }
    actual fun shareText(text: String, title: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
            if (title.isNotEmpty()) {
                putExtra(Intent.EXTRA_SUBJECT, title)
            }
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val chooser = Intent.createChooser(intent, title.ifEmpty { "Share via" })
        context.startActivity(chooser)
    }
}

