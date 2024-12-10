package com.valmiraguiar.home.presentation.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.valmiraguiar.core.sharedentity.champion.Champion
import com.valmiraguiar.core.theme.LeagueWikiTheme

@Composable
fun ChampionItem(
    modifier: Modifier = Modifier,
    champion: Champion,
    championImgUrl: String,
    onClickAction: (championData: String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        shape = LeagueWikiTheme.shape.roundedCorner,
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        onClick = {
            onClickAction(
                champion.id
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(
                model = championImgUrl,
                contentDescription = "Champion image",
                contentScale = ContentScale.FillBounds,
                loading = { OnLoadingImage() }
            )
            Box(
                modifier = Modifier.background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ), startY = 300f
                    )
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = champion.name,
                        color = LeagueWikiTheme.colorScheme.onPrimary,
                        style = LeagueWikiTheme.typography.titleLarge,
                    )
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