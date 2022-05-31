package com.eozsahin.accordion.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eozsahin.accordion.R

val quickSandFontFamily = FontFamily(
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    //defaultFontFamily = fontFamily
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val tags = Typography.body1.copy(fontFamily = quickSandFontFamily, fontWeight = FontWeight.Bold)

val bodyBold = Typography.body1.copy(fontWeight = FontWeight.Bold)

val accordionHeaderStyle = Typography.h6.copy(fontWeight = FontWeight.Bold)