package com.yoomy.nest.mobile.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
open class NestDimens {

    val spacingScale: SpacingScale = SpacingScale

    open val padding: Dp = SpacingScale.M

    val statusBarHeight: Dp = 24.dp

    object SpacingScale {
        val None = 0.dp
        val XS4 = 1.dp
        val XS3 = 2.dp
        val XS2 = 4.dp
        val XS = 8.dp
        val S = 12.dp
        val M = 16.dp
        val L = 20.dp
        val XL = 24.dp
        val XL2 = 32.dp
        val XL3 = 40.dp
        val XL4 = 48.dp
        val XL5 = 56.dp
    }
}

@Immutable
class NestDimensMedium : NestDimens() {
    override val padding: Dp = SpacingScale.XL
}

@Immutable
class NestDimensExpanded : NestDimens() {
    override val padding: Dp = SpacingScale.XL
}