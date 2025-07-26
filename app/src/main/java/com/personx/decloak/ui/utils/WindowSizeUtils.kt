package com.personx.decloak.ui.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.window.layout.WindowMetricsCalculator
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun getWindowSizeClass(): WindowSizeClass {
    val context = LocalContext.current
    val activity = context.findActivity()
    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)
    val widthDp = metrics.bounds.width() / context.resources.displayMetrics.density
    val heightDp = metrics.bounds.height() / context.resources.displayMetrics.density

    return WindowSizeClass.calculateFromSize(androidx.compose.ui.unit.DpSize(widthDp.dp, heightDp.dp))
}

// Helper extension to extract Activity from Context
fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Activity not found from context")
}
