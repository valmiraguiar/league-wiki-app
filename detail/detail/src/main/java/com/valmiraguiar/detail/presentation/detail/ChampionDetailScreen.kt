package com.valmiraguiar.detail.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.detail.BuildConfig
import com.valmiraguiar.detail.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionDetailScreen(
    modifier: Modifier = Modifier,
    championId: String,
    viewModel: ChampionDetailViewModel,
    onClickNavigation: () -> Unit
) {
    val detailChampionState = viewModel.detailChampionState.collectAsState()

    LaunchedEffect(detailChampionState) {
        viewModel.loadChampionDetail(championId)
    }

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

        when (detailChampionState.value) {
            is DetailChampionState.Error -> {
                Text("ERROR")
            }

            DetailChampionState.Loading -> {
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

            is DetailChampionState.Success -> {
                val championDetailResponse =
                    (detailChampionState.value as DetailChampionState.Success).championDetail

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    SubcomposeAsyncImage(
                        model = "${BuildConfig.BASE_SPLASH_URL}/${championDetailResponse.id}_0.jpg",
                        contentDescription = stringResource(R.string.champion_image_description),
                        contentScale = ContentScale.FillBounds,
                        loading = { OnLoadingImage() },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = championDetailResponse.name,
                            color = LeagueWikiTheme.colorScheme.onBackground,
                            style = LeagueWikiTheme.typography.labelLarge,
                        )

                        Text(
                            text = championDetailResponse.title,
                            color = LeagueWikiTheme.colorScheme.onBackground,
                            style = LeagueWikiTheme.typography.labelNormal,
                        )

                        Text(
                            text = championDetailResponse.lore,
                            color = LeagueWikiTheme.colorScheme.onBackground,
                            style = LeagueWikiTheme.typography.body,
                            modifier = Modifier.padding(top = 32.dp),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }


    }
}

@Composable
private fun OnLoadingImage() {
    Box(
        modifier = Modifier
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