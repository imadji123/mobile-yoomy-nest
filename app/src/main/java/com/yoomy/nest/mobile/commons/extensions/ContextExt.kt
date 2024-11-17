package com.yoomy.nest.mobile.commons.extensions

import android.content.Context
import com.yoomy.nest.mobile.commons.COMPACT_DEVICE_WIDTH_SIZE

val Context.isTablet: Boolean get() = resources.configuration.smallestScreenWidthDp >= COMPACT_DEVICE_WIDTH_SIZE