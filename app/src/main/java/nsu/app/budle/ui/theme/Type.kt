package nsu.app.budle.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.budle.R

val fonts = FontFamily(
    Font(R.font.gilroy_semibold, weight = FontWeight.SemiBold),
    Font(R.font.gilroy_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(R.font.gilroy_bold, weight = FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ), titleLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic
    ), labelSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ), titleMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ), titleSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    )
)
