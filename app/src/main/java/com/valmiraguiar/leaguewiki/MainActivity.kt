package com.valmiraguiar.leaguewiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.valmiraguiar.leaguewiki.navigation.DefaultNavigator
import com.valmiraguiar.leaguewiki.navigation.MainNavigation
import com.valmiraguiar.leaguewiki.theme.LoLWikiTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val defaultNavigator: DefaultNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoLWikiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavigation(
                        modifier = Modifier.padding(innerPadding),
                        defaultNavigator = defaultNavigator
                    )
                }
            }
        }
    }
}