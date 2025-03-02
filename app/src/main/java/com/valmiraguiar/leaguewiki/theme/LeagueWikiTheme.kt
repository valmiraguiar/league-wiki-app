package com.valmiraguiar.leaguewiki.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.valmiraguiar.core.theme.AppColorScheme
import com.valmiraguiar.core.theme.Black100
import com.valmiraguiar.core.theme.Blue100
import com.valmiraguiar.core.theme.Blue200
import com.valmiraguiar.core.theme.Blue300
import com.valmiraguiar.core.theme.LocalAppColorScheme
import com.valmiraguiar.core.theme.LocalAppShape
import com.valmiraguiar.core.theme.LocalAppSize
import com.valmiraguiar.core.theme.LocalAppTypography
import com.valmiraguiar.core.theme.White100
import com.valmiraguiar.core.theme.shape
import com.valmiraguiar.core.theme.size
import com.valmiraguiar.core.theme.typography

private val lightColorScheme = AppColorScheme(
    background = White100,
    onBackground = Black100,
    primary = Blue100,
    onPrimary = White100,
    secondary = Blue300,
    onSecondary = White100,
    tertiary = Blue200,
    onTertiary = White100
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

