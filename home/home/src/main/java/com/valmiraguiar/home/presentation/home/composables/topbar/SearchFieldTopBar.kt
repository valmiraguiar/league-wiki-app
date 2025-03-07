package com.valmiraguiar.home.presentation.home.composables.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.home.R

@Composable
fun SearchFieldTopBar(
    modifier: Modifier = Modifier,
    searchBarIsExpandedState: Boolean,
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterEnd
    ) {
        AnimatedVisibility(
            visible = searchBarIsExpandedState,
            enter = expandHorizontally(expandFrom = Alignment.End) + fadeIn(),
            exit = shrinkHorizontally(shrinkTowards = Alignment.End) + fadeOut(),
        ) {
            TextField(
                value = searchText,
                onValueChange = { onSearchTextChange(it) },
                placeholder = {
                    Text(
                        stringResource(R.string.sort_menu_search_bar_placeholder),
                        style = LeagueWikiTheme.typography.labelSmall
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = LeagueWikiTheme.colorScheme.tertiary,
                    unfocusedContainerColor = LeagueWikiTheme.colorScheme.tertiary,
                    disabledContainerColor = LeagueWikiTheme.colorScheme.tertiary,
                    errorContainerColor = LeagueWikiTheme.colorScheme.tertiary,
                    focusedPlaceholderColor = LeagueWikiTheme.colorScheme.onPrimary,
                    errorPlaceholderColor = LeagueWikiTheme.colorScheme.onPrimary,
                    disabledPlaceholderColor = LeagueWikiTheme.colorScheme.onPrimary,
                    unfocusedPlaceholderColor = LeagueWikiTheme.colorScheme.onPrimary,
                    focusedTextColor = LeagueWikiTheme.colorScheme.onPrimary,
                    errorTextColor = LeagueWikiTheme.colorScheme.onPrimary,
                    disabledTextColor = LeagueWikiTheme.colorScheme.onPrimary,
                    unfocusedTextColor = LeagueWikiTheme.colorScheme.onPrimary,
                    focusedIndicatorColor = LeagueWikiTheme.colorScheme.onPrimary,
                    errorIndicatorColor = LeagueWikiTheme.colorScheme.onPrimary,
                    disabledIndicatorColor = LeagueWikiTheme.colorScheme.onPrimary,
                    unfocusedIndicatorColor = LeagueWikiTheme.colorScheme.onPrimary,
                    cursorColor = LeagueWikiTheme.colorScheme.onPrimary,
                ),
                modifier = Modifier
                    .widthIn(1.dp, Dp.Infinity)
                    .fillMaxWidth()
            )
        }
    }
}