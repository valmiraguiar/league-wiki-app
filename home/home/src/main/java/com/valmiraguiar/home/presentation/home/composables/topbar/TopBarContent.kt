package com.valmiraguiar.home.presentation.home.composables.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.home.R
import com.valmiraguiar.home.presentation.home.ChampionSort

@Composable
fun SortMenu(
    onSort: (sortFrom: ChampionSort) -> Unit,
    searchBarIsExpandedState: Boolean,
    onChangeSearchBarIsExpandedState: () -> Unit,
    searchTextState: String,
    onSearchTextState: (String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    var expandedSortMenu by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopBarTitle(
            searchBarIsExpandedState = searchBarIsExpandedState
        )

        SearchFieldTopBar(
            modifier = Modifier.weight(1f),
            searchBarIsExpandedState = searchBarIsExpandedState,
            searchText = searchTextState
        ) {
            onSearchTextState(it)
        }

        TopBarActionButtons(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(42.dp),
            onChangeSearchBarIsExpandedState = onChangeSearchBarIsExpandedState,
            searchBarIsExpandedState = searchBarIsExpandedState,
            onSort = onSort,
            onSearchTextState = onSearchTextState,
            showMenu = showMenu,
            onChangeShowMenu = { showMenu = it },
            expandedSortMenu = expandedSortMenu,
            onChangeExpandedSortMenu = { expandedSortMenu = it },
        )
    }
}

@Composable
fun TopBarTitle(
    searchBarIsExpandedState: Boolean,
) {
    AnimatedVisibility(
        visible = !searchBarIsExpandedState,
        enter = expandHorizontally() + fadeIn(),
        exit = shrinkHorizontally() + fadeOut()
    ) {
        Text(
            text = stringResource(R.string.app_title),
            color = LeagueWikiTheme.colorScheme.onSecondary,
            style = LeagueWikiTheme.typography.titleNormal,
            modifier = Modifier.widthIn(46.dp)
        )
    }
}

@Preview(device = Devices.PIXEL_C, showBackground = true)
@Composable
private fun ShowPreview() {
    SortMenu(
        onSort = {},
        searchBarIsExpandedState = true,
        onChangeSearchBarIsExpandedState = { },
        searchTextState = "",
        onSearchTextState = { }
    )
}