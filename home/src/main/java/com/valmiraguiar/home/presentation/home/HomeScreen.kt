package com.valmiraguiar.home.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.home.presentation.home.composables.ChampionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, onClickNavigation: () -> Unit) {
    val championList = listOf(
        "Graves",
        "Ahri",
        "Aatrox",
        "Khazix",
        "Graves",
        "Ahri",
        "Aatrox",
        "Khazix",
        "Graves",
        "Ahri",
        "Aatrox",
        "Khazix"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("League Wiki", color = LeagueWikiTheme.colorScheme.onSecondary) },
                colors = TopAppBarDefaults.topAppBarColors(LeagueWikiTheme.colorScheme.secondary)
            )
        },
        modifier = Modifier.background(LeagueWikiTheme.colorScheme.background)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            userScrollEnabled = true,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(championList) { champion ->
                ChampionItem(modifier = modifier.padding(vertical = 8.dp), championName = champion, onClickNavigation)
            }
        }
    }
}