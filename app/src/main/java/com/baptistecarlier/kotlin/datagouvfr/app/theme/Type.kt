package com.baptistecarlier.kotlin.datagouvfr.app.theme

import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.baptistecarlier.kotlin.datagouvfr.app.R

private val LatoFont = FontFamily(
    Font(R.font.lato_thin, FontWeight.W200),
    Font(R.font.lato_light, FontWeight.W300),
    Font(R.font.lato_regular, FontWeight.W400),
    Font(R.font.lato_medium, FontWeight.W500),
    Font(R.font.lato_semibold, FontWeight.W600),
    Font(R.font.lato_bold, FontWeight.W700),
    Font(R.font.lato_black, FontWeight.W800)
)

val Typography.odfTypeInfos: TextStyle
    @Composable get() = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )

val LatoTypography = Typography(
    h1 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W300,
        fontSize = 56.sp
    ),
    h2 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W300,
        fontSize = 44.sp
    ),
    h3 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W700,
        fontSize = 36.sp
    ),
    h4 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W500,
        fontSize = 32.sp
    ),
    h5 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W700,
        fontSize = 28.sp
    ),
    h6 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W800,
        lineHeight = 24.sp,
        fontSize = 18.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W500,
        lineHeight = 24.sp,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W500,
        lineHeight = 28.sp,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W400,
        lineHeight = 20.sp,
        fontSize = 14.sp
    ),
    button = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W600,
        lineHeight = 16.sp,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W500,
        lineHeight = 16.sp,
        fontSize = 14.sp
    ),
    overline = TextStyle(
        fontFamily = LatoFont,
        fontWeight = FontWeight.W500,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        letterSpacing = 0.16.em,
    )
)