package com.valmiraguiar.home.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.home.BuildConfig
import com.valmiraguiar.home.R
import com.valmiraguiar.home.presentation.home.composables.ChampionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onClickNavigation: (championId: String) -> Unit
) {
    val championListState = viewModel.championListState.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_title),
                        color = LeagueWikiTheme.colorScheme.onSecondary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(LeagueWikiTheme.colorScheme.secondary)
            )
        },
        modifier = Modifier.background(LeagueWikiTheme.colorScheme.background)
    ) { innerPadding ->
        when (championListState.value) {
            ChampionListState.Loading -> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = LeagueWikiTheme.colorScheme.primary,
                        strokeWidth = 2.dp,
                        strokeCap = StrokeCap.Round
                    )
                }
            }

            is ChampionListState.Error -> {
                Text("ERROR")
            }

            is ChampionListState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding),
                    userScrollEnabled = true,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items((championListState.value as ChampionListState.Success).championList) { champion ->
                        ChampionItem(
                            modifier = modifier.padding(vertical = 8.dp),
                            championName = champion.name,
                            championId = champion.id,
                            imgUrl = "${BuildConfig.BASE_SPLASH_URL}/${champion.id}_0.jpg",
                            onClickAction = onClickNavigation
                        )
                    }
                }
            }
        }
    }
}