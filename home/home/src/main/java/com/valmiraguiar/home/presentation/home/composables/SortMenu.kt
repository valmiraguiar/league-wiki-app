package com.valmiraguiar.home.presentation.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme

@Composable
fun SortMenu() {
    var showMenu by remember { mutableStateOf(false) }
    var expandedSortMenu by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { showMenu = !showMenu }) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Sort",
                tint = LeagueWikiTheme.colorScheme.onPrimary
            )
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Ordenar por")

                DropdownMenuItem(
                    onClick = { expandedSortMenu = !expandedSortMenu },
                    text = { Text("Nome") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Sort",
                            tint = LeagueWikiTheme.colorScheme.onBackground,
                        )
                    }
                )
            }
        }

        DropdownMenu(
            expanded = expandedSortMenu,
            onDismissRequest = {
                expandedSortMenu = false
                showMenu = false
            }
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                DropdownMenuItem(
                    onClick = { /*TODO*/ },
                    text = { Text("Crescente") }
                )

                DropdownMenuItem(
                    onClick = { /*TODO*/ },
                    text = { Text("Decrescente") }
                )
            }
        }
    }
}