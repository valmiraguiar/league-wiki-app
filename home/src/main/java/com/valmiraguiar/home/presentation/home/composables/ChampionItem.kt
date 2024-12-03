package com.valmiraguiar.home.presentation.home.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valmiraguiar.core.theme.LeagueWikiTheme
import com.valmiraguiar.home.R

@Composable
fun ChampionItem(modifier: Modifier = Modifier, championName: String) {
    val currentContext = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp),
        shape = LeagueWikiTheme.shape.roundedCorner,
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        onClick = {
            Toast.makeText(currentContext, "Item -> Graves", Toast.LENGTH_SHORT).show()
        }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.graves),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds
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
                        text = championName,
                        color = LeagueWikiTheme.colorScheme.onPrimary,
                        style = LeagueWikiTheme.typography.titleLarge,
                    )
                }
            }
        }
    }

}