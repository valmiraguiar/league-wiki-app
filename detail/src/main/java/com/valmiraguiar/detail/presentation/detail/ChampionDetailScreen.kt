package com.valmiraguiar.detail.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ChampionDetailScreen(modifier: Modifier = Modifier, onClickNavigation: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Champion Detail Feature", modifier = Modifier.clickable { onClickNavigation.invoke() })
    }
}