package fit.budle.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fit.budle.R

val fonts = FontFamily(
    Font(R.font.gilroy_semibold, weight = FontWeight.SemiBold),
    Font(R.font.gilroy_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.gilroy_bold, weight = FontWeight.Bold)
)

val Typography = Typography(
    body1 = TextStyle(
        // Text/Plain
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    h2 = TextStyle(
        // Headings/Heading 1
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    ),
    h3 = TextStyle(
        // Text/Title 3
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    subtitle1 = TextStyle(
        // Form/Numbers
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),
    subtitle2 = TextStyle(
        // Text/Message
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        fontSize = 20.sp,
        lineHeight = 24.sp
    )
)
