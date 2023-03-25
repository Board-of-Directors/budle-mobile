package fit.budle.components.atoms.tags

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite

@Composable
fun BudleRateTag(
    tag: String,
    width: Dp = 55.dp,
    color: Color = mainWhite,
    textColor: Color = mainBlack
) {
    Card(
        colors = CardDefaults.cardColors(color),
        modifier = Modifier
            .width(width)
            .height(28.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = tag,
                style = MaterialTheme.typography.bodyMedium,
                color = textColor,
            )
        }
    }
}