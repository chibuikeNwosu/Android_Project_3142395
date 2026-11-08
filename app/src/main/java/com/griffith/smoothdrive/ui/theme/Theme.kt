package com.griffith.smoothdrive.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Forest,
    onPrimary = PureWhite,
    primaryContainer = Sage,
    onPrimaryContainer = ForestDeep,
    secondary = Bark,
    onSecondary = PureWhite,
    secondaryContainer = Sand,
    onSecondaryContainer = BarkDeep,
    tertiary = Moss,
    onTertiary = PureWhite,
    background = Cream,
    onBackground = Charcoal,
    surface = Cream,
    onSurface = Charcoal,
    surfaceVariant = Mist,
    onSurfaceVariant = Stone,
    outline = Stone,
    surfaceContainerLowest = PureWhite,
    surfaceContainerLow = Cream,
    surfaceContainer = Mist,
    surfaceContainerHigh = Mist,
    surfaceContainerHighest = Mist
)

private val DarkColors = darkColorScheme(
    primary = ForestLight,
    onPrimary = ForestOnDark,
    primaryContainer = ForestContainerDark,
    onPrimaryContainer = Sage,
    secondary = BarkLight,
    onSecondary = BarkOnDark,
    secondaryContainer = BarkContainerDark,
    onSecondaryContainer = Sand,
    background = NightGreen,
    onBackground = NightText,
    surface = NightGreen,
    onSurface = NightText,
    surfaceContainerLowest = NightGreen,
    surfaceContainerLow = NightGreen,
    surfaceContainer = NightSurface,
    surfaceContainerHigh = NightSurface,
    surfaceContainerHighest = NightSurface
)

@Composable
fun SmoothDriveTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}