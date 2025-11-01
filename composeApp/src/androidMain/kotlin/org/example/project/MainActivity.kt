package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.commons.TranslationUtils
import org.example.project.di.KoinInit

class MainActivity : ComponentActivity() {

    val translationUtils by lazy {
        TranslationUtils(this@MainActivity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            KoinInit(translationUtils)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}