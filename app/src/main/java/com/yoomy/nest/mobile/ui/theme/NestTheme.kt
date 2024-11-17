package com.yoomy.nest.mobile.ui.theme

import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

object NestTheme {
    val colors: NestColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalNestColorScheme.current

    val dimens: NestAdaptiveDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalNestAdaptiveDimens.current
}

@Composable
fun NestTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkTheme -> darkColorScheme
        else -> lightColorScheme
    }

    val nestColorScheme = if (isDarkTheme) nestLight else nestDark

    val windowSizeClass = calculateWindowSizeClass(LocalConfiguration.current)
    val adaptiveDimens = NestAdaptiveDimens.getAdaptiveDimens(windowSizeClass)

    CompositionLocalProvider(
        LocalNestColorScheme provides nestColorScheme,
        LocalNestAdaptiveDimens provides adaptiveDimens
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal fun calculateWindowSizeClass(configuration: Configuration): WindowSizeClass {
    val size = DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
    return WindowSizeClass.calculateFromSize(size)
}

@Composable
fun calculateMaxScreenWidth(): Dp {
    val windowSizeClass = calculateWindowSizeClass(LocalConfiguration.current)
    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Medium -> 500.dp
        WindowWidthSizeClass.Expanded -> 600.dp
        else -> LocalConfiguration.current.screenWidthDp.dp
    }
}