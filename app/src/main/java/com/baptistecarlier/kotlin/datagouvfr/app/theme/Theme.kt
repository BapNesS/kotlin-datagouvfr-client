package com.baptistecarlier.kotlin.datagouvfr.app.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val OdfLightColors = lightColors(
    onBackground = OdfColorTangaroa,
    background = OdfColorWhite,

    onSurface = OdfColorWhite,
    surface = OdfColorWashme,

    onPrimary = OdfColorWhite,
    primary = OdfColorFoutain,
    primaryVariant = OdfColorPurple,

    onSecondary = OdfColorWhite,
    secondary = OdfColorVenti,
    secondaryVariant = OdfColorPurple,

    onError = OdfColorWashme,
    error = OdfColorErrorDark,
)
private val OdfFoutainLightColors = lightColors(
    onBackground = OdfColorWhite,
    background = OdfColorChill,

    onSurface = OdfColorWhite,
    surface = OdfColorChill,

    onPrimary = OdfColorWhite,
    primary = OdfColorChill,
    primaryVariant = OdfColorPurple,

    onSecondary = OdfColorWhite,
    secondary = OdfColorVenti,
    secondaryVariant = OdfColorPurple,

    onError = OdfColorWashme,
    error = OdfColorErrorDark,
)

private val OdfDarkColors = darkColors(
    onBackground = OdfColorWashme,
    background = OdfColorTangaroa,

    onSurface = OdfColorWashme,
    surface = OdfColorInkjet,

    onPrimary = OdfColorWhite,
    primary = OdfColorFoutain,
    primaryVariant = OdfColorPurple,

    onSecondary = OdfColorWhite,
    secondary = OdfColorVenti,
    secondaryVariant = OdfColorPurple,

    onError = OdfColorWashme,
    error = OdfColorErrorLight,
)


private val OdfFoutainDarkColors = darkColors(
    onBackground = OdfColorWhite,
    background = OdfColorVenti,

    onSurface = OdfColorWhite,
    surface = OdfColorTangaroa,

    onPrimary = OdfColorWhite,
    primary = OdfColorFoutain,
    primaryVariant = OdfColorPurple,

    onSecondary = OdfColorWhite,
    secondary = OdfColorVenti,
    secondaryVariant = OdfColorPurple,

    onError = OdfColorWashme,
    error = OdfColorErrorLight,
)

@Composable
fun OdfDefaultTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (isDarkTheme) OdfDarkColors else OdfLightColors,
        typography = LatoTypography,
        shapes = OdfShapes
    ) {
        content()
    }
}

@Composable
fun OdfFoutainTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = if (isDarkTheme) OdfFoutainDarkColors else OdfFoutainLightColors,
        typography = LatoTypography,
        shapes = OdfShapes
    ) {
        content()
    }
}