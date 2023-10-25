package com.imstudio.pollen.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.imstudio.pollen.R

// Set of Material typography styles to start with
val Gabarito = FontFamily(
    Font(R.font.gabarito_regular, FontWeight.Normal),
    Font(R.font.gabarito_medium, FontWeight.Medium),
    Font(R.font.gabarito_bold, FontWeight.Bold)
)
val GabaritoTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = Gabarito,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Gabarito,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Gabarito,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Gabarito,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Gabarito,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)