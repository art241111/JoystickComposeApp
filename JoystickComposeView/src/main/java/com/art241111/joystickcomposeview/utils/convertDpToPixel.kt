package com.art241111.joystickcomposeview.utils

import android.content.Context
import androidx.compose.ui.unit.Dp

// Kotlin
fun Dp.toFloat(context: Context): Float =
    this.value * context.resources.displayMetrics.density
