package com.valmiraguiar.leaguewiki.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


private val lightColorScheme = AppColorScheme(
    background = White100,
    onBackground = Black100,
    primary = Blue100,
    onPrimary = White100,
    secondary = Blue300,
    onSecondary = Black100,
)

@Composable
fun LoLWikiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme: AppColorScheme = when {
//        darkTheme -> lightColorScheme //TODO - Create dark theme
        else -> lightColorScheme
    }
    val rippleIndication = ripple()


    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalIndication provides rippleIndication,
        content = content
    )
}


object LolWikiTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current
}

