package com.valmiraguiar.home.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.home.BuildConfig
import com.valmiraguiar.home.R
import com.valmiraguiar.home.presentation.home.composables.ChampionItem
import com.valmiraguiar.home.presentation.home.composables.SortMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    onClickNavigation: (championId: String) -> Unit
) {
    val championListState = viewModel.championListState.collectAsState()
    val searchQueryState = viewModel.searchQueryState.collectAsState()

    var searchBarIsExpandedState by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadChampions()
    }

    fun onChampionItemClick(championId: String) {
        viewModel.onSearchQueryChange("")
        onClickNavigation(championId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = !searchBarIsExpandedState,
                        enter = expandHorizontally(
                            animationSpec = tween(
                                durationMillis = 100
                            )
                        ) + fadeIn(),
                        exit = shrinkHorizontally(
                            animationSpec = tween(
                                durationMillis = 100
                            )
                        ) + fadeOut()
                    ) {
                        Text(
                            text = stringResource(R.string.app_title),
                            color = LeagueWikiTheme.colorScheme.onSecondary
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(LeagueWikiTheme.colorScheme.secondary),
                actions = {
                    SortMenu(
                        onSort = viewModel::sortChampions,
                        searchBarIsExpandedState = searchBarIsExpandedState,
                        onChangeSearchBarIsExpandedState = {
                            searchBarIsExpandedState = !searchBarIsExpandedState
                        },
                        searchTextState = searchQueryState.value,
                        onSearchTextState = viewModel::onSearchQueryChange
                    )
                },
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
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.generic_error),
                        color = LeagueWikiTheme.colorScheme.onBackground,
                        style = LeagueWikiTheme.typography.titleLarge,
                    )
                }

            }

            is ChampionListState.Success -> {
                val items = (championListState.value as ChampionListState.Success).championList
                val championFilteredList =
                    viewModel.getFilteredChampionList(items, searchQueryState.value)

                LazyColumn(
                    modifier = Modifier.padding(innerPadding),
                    userScrollEnabled = true,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(championFilteredList) { champion ->
                        ChampionItem(
                            modifier = modifier.padding(vertical = 8.dp),
                            champion = champion,
                            championImgUrl = "${BuildConfig.BASE_SPLASH_URL}/${champion.id}_0.jpg",
                            onClickAction = ::onChampionItemClick
                        )
                    }
                }
            }
        }
    }
}