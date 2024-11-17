package com.yoomy.nest.mobile.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val darkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

val lightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

//    background = Color(0xFFFFFBFE),
//    surface = Color(0xFFFFFBFE),
//    onPrimary = Color.White,
//    onSecondary = Color.White,
//    onTertiary = Color.White,
//    onBackground = Color(0xFF1C1B1F),
//    onSurface = Color(0xFF1C1B1F)
)

@Immutable
data class NestColorFamily(
    val primary: Color
)

@Immutable
data class NestColorScheme(
    val primary: Color
)

val nestLight = NestColorScheme(
    primary = Purple80
)

val nestDark = NestColorScheme(
    primary = Purple40
)

internal val LocalNestColorScheme = staticCompositionLocalOf { nestLight }