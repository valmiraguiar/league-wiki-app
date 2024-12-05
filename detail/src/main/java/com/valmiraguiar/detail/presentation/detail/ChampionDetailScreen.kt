package com.valmiraguiar.detail.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.detail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionDetailScreen(
    modifier: Modifier = Modifier,
    onClickNavigation: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("League Wiki", color = LeagueWikiTheme.colorScheme.onSecondary) },
                colors = TopAppBarDefaults.topAppBarColors(LeagueWikiTheme.colorScheme.secondary),
                navigationIcon = {
                    IconButton(
                        onClick = onClickNavigation
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back action champion detail",
                            tint = LeagueWikiTheme.colorScheme.onSecondary
                        )
                    }
                }
            )
        },
        modifier = modifier.background(LeagueWikiTheme.colorScheme.background)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.graves),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Graves",
                    color = LeagueWikiTheme.colorScheme.onBackground,
                    style = LeagueWikiTheme.typography.labelLarge,
                )

                Text(
                    text = "o Foragido",
                    color = LeagueWikiTheme.colorScheme.onBackground,
                    style = LeagueWikiTheme.typography.labelNormal,
                )

                Text(
                    text = "Malcolm Graves, mercenário, apostador e bandido de renome, é um homem procurado em cada uma das cidades e impérios que visitou. Apesar do seu temperamento explosivo, ele é dono de uma noção rigorosa de honra entre criminosos, normalmente aplicada com o fogo da sua espingarda, Destino. Nos últimos anos, ele reconciliou uma parceria problemática com Twisted Fate e, juntos, eles prosperaram mais uma vez no tumulto do submundo criminoso de Águas de Sentina.Malcolm Graves, mercenário, apostador e bandido de renome, é um homem procurado em cada uma das cidades e impérios que visitou. Apesar do seu temperamento explosivo, ele é dono de uma noção rigorosa de honra entre criminosos, normalmente aplicada com o fogo da sua espingarda, Destino. Nos últimos anos, ele reconciliou uma parceria problemática com Twisted Fate e, juntos, eles prosperaram mais uma vez no tumulto do submundo criminoso de Águas de Sentina.",
                    color = LeagueWikiTheme.colorScheme.onBackground,
                    style = LeagueWikiTheme.typography.body,
                    modifier = Modifier.padding(top = 32.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}