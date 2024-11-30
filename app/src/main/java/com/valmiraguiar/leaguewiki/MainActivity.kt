package com.valmiraguiar.leaguewiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.valmiraguiar.leaguewiki.ui.theme.LoLWikiTheme
import com.valmiraguiar.leaguewiki.ui.theme.LolWikiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoLWikiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Teste(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Teste(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = LolWikiTheme.colorScheme.background,
    ) {
        Greeting()
    }
}

@Composable
fun Greeting() {
    Text(
        color = LolWikiTheme.colorScheme.secondary,
        text = "Hello World!",
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoLWikiTheme {
        Teste(Modifier)
    }
}