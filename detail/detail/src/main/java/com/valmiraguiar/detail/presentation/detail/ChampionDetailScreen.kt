package com.valmiraguiar.detail.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.detail.BuildConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChampionDetailScreen(
    modifier: Modifier = Modifier,
    championId: String,
    viewModel: ChampionDetailViewModel,
    onClickNavigation: () -> Unit
) {
    val detailChampionState = viewModel.detailChampionState.collectAsStateWithLifecycle()

    LaunchedEffect(LocalLifecycleOwner.current) {
        viewModel.loadChampionDetail(championId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
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
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            start = 0.dp,
                            end = 0.dp
                        )
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${BuildConfig.BASE_SPLASH_URL}/${championDetailResponse.id}_0.jpg")
                            .crossfade(true).build(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = null
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