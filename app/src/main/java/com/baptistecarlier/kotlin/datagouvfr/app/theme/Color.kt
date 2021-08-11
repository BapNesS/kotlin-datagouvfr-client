package com.baptistecarlier.kotlin.datagouvfr.app.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val OdfColorPurple = Color(0xFF494E77)
val OdfColorVenti = Color(0xFF5B70B8)
val OdfColorGreen = Color(0xFF4CA98D)
val OdfColorFoutain = Color(0xFF1F70FF)
val OdfColorChill = Color(0xFF198CF2)
val OdfColorFennel = Color(0xFF00BE7B)
val OdfColorTexas = Color(0xFFFF9827)

val OdfColorWhite = Color(0xFFFFFFFF)
val OdfColorWashme = Color(0xFFF8FAFD)
val OdfColorCotton = Color(0xFFF1F6FC)
val OdfColorInkjet = Color(0xFF41546C)
val OdfColorTangaroa = Color(0xFF202B3A)
val OdfColorBlack = Color(0xFF000000)

val OdfColorErrorLight = Color(0xFFEF5350)
val OdfColorErrorDark = Color(0xFFB00020)

val Colors.odfTextSecondary: Color
    @Composable get() = if (isLight) OdfColorInkjet else OdfColorWashme

val Colors.odfResources: Color
    @Composable get() = if (isLight) OdfColorVenti else OdfColorChill

val Colors.odfReuses: Color
    @Composable get() = if (isLight) OdfColorGreen else OdfColorFennel

val Colors.odfFollowers: Color
    @Composable get() = if (isLight) OdfColorTexas else OdfColorTexas