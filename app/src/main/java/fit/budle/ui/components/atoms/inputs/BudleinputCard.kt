package fit.budle.ui.components.atoms.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.lightBlue

@Composable
fun BudleInputCard(
    contentColor: Color,
    iconId: Int,
    inputText: String,
    stroke: Stroke,
    height: Dp = 130.dp,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .height(height)
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(
                    color = lightBlue,
                    style = stroke,
                    cornerRadius = CornerRadius(15.dp.toPx())
                )
            }
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(lightBlue),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "Выберите файл",
                    tint = contentColor
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = inputText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = contentColor
                )
            }
        }
    }
}