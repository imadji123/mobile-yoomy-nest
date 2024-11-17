package com.yoomy.nest.mobile.ui.theme

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
class NestAdaptiveDimens(
    val baseDimens: NestDimens
) {
    companion object {
        internal val nestDimensCompact = NestAdaptiveDimens(
            baseDimens = NestDimens()
        )

        private val nestDimensMedium = NestAdaptiveDimens(
            baseDimens = NestDimensMedium()
        )

        private val nestDimensExpanded = NestAdaptiveDimens(
            baseDimens = NestDimensExpanded()
        )

        fun getAdaptiveDimens(windowClassSize: WindowSizeClass?): NestAdaptiveDimens {
            return when (windowClassSize?.widthSizeClass) {
                WindowWidthSizeClass.Compact -> nestDimensCompact
                WindowWidthSizeClass.Medium -> nestDimensMedium
                WindowWidthSizeClass.Expanded -> nestDimensExpanded
                else -> nestDimensCompact
            }
        }
    }
}

internal val LocalNestAdaptiveDimens =
    staticCompositionLocalOf { NestAdaptiveDimens.nestDimensCompact }