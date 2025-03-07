package com.valmiraguiar.home.presentation.home.composables.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.home.R
import com.valmiraguiar.home.presentation.home.ChampionSort
import com.valmiraguiar.home.presentation.home.SortEnum

@Composable
fun TopBarActionButtons(
    modifier: Modifier = Modifier,
    onChangeSearchBarIsExpandedState: () -> Unit,
    searchBarIsExpandedState: Boolean,
    onSort: (sortFrom: ChampionSort) -> Unit,
    onSearchTextState: (String) -> Unit,
    showMenu: Boolean,
    onChangeShowMenu: (showMenu: Boolean) -> Unit,
    expandedSortMenu: Boolean,
    onChangeExpandedSortMenu: (expandMenu: Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {
            onChangeSearchBarIsExpandedState()
            if (!searchBarIsExpandedState) onSearchTextState("")
        }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.sort_menu_title),
                tint = LeagueWikiTheme.colorScheme.onPrimary
            )
        }

        IconButton(onClick = { onChangeShowMenu(!showMenu) }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = stringResource(R.string.sort_menu_title),
                tint = LeagueWikiTheme.colorScheme.onPrimary
            )
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { onChangeShowMenu(!showMenu) }
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.sort_menu_title),
                    style = LeagueWikiTheme.typography.labelSmall
                )

                DropdownMenuItem(
                    onClick = { onChangeExpandedSortMenu(!expandedSortMenu) },
                    text = {
                        Text(
                            text = stringResource(R.string.sort_menu_name_sort),
                            style = LeagueWikiTheme.typography.labelSmall
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = stringResource(R.string.sort_menu_name_sort),
                            tint = LeagueWikiTheme.colorScheme.onBackground,
                        )
                    }
                )
            }
        }

        DropdownMenu(
            expanded = expandedSortMenu,
            onDismissRequest = {
                onChangeExpandedSortMenu(false)
                onChangeShowMenu(false)
            }
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                DropdownMenuItem(
                    onClick = {
                        onSort(ChampionSort.Name(SortEnum.ASC))
                        onChangeExpandedSortMenu(false)
                        onChangeShowMenu(false)
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.sort_menu_crescent),
                            style = LeagueWikiTheme.typography.labelSmall
                        )
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        onSort(ChampionSort.Name(SortEnum.DESC))
                        onChangeExpandedSortMenu(false)
                        onChangeShowMenu(false)
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.sort_menu_descending),
                            style = LeagueWikiTheme.typography.labelSmall
                        )
                    }
                )
            }
        }
    }
}