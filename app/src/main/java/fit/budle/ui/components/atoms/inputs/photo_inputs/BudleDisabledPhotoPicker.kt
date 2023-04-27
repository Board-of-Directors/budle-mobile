package fit.budle.ui.components.atoms.inputs.photo_inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.textGray

@Composable
fun BudleDisabledPhotoPicker(
    stroke: Stroke,
    onClick: () -> (Unit)
){
    Card(
        modifier = Modifier
            .padding(top = 10.dp)
            .height(130.dp)
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(
                    color = textGray,
                    style = stroke,
                    cornerRadius = CornerRadius(15.dp.toPx())
                )
            }
            .clickable(
                onClick = onClick
            ),
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
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Выберите фотографию",
                    tint = textGray
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "Выберите фотографию",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray
                )
            }
        }
    }
}