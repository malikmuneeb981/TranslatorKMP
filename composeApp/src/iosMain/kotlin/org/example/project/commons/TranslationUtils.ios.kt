package org.example.project.commons

import platform.AVFAudio.AVSpeechBoundary
import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIPasteboard

actual class TranslationUtils {
    actual fun copyToClipboard(text: String) {
        UIPasteboard.generalPasteboard.string = text
    }
    private var synthesizer: AVSpeechSynthesizer ?= null
    actual fun startTTS(text: String) {
        synthesizer=AVSpeechSynthesizer()
        val utterance = AVSpeechUtterance.speechUtteranceWithString(text)
        utterance.voice = AVSpeechSynthesisVoice.voiceWithLanguage("en-US")
        synthesizer?.speakUtterance(utterance)
    }

    actual fun stopTTS() {
        synthesizer?.stopSpeakingAtBoundary(AVSpeechBoundary.AVSpeechBoundaryImmediate)
    }
    actual fun shareText(text: String, title: String) {
        val items = listOf(text)
        val activityViewController = UIActivityViewController(items, null)
        val keyWindow = UIApplication.sharedApplication.keyWindow
        val rootViewController = keyWindow?.rootViewController

        rootViewController?.presentViewController(
            activityViewController,
            animated = true,
            completion = null
        )
    }

}

