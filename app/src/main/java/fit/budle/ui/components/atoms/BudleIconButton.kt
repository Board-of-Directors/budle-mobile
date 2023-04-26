package fit.budle.ui.components.atoms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray

@Composable
fun BudleIconButton(
    iconDescription: String,
    iconId: Int,
    modifier: Modifier = Modifier,
    buttonColor: Color = mainWhite,
    crossColor: Color = textGray,
    elevation: Dp = 4.dp,
    onClick: () -> Unit = {}
){
    Card(
        colors = CardDefaults.cardColors(buttonColor),
        modifier = modifier,
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(elevation),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = iconDescription,
                    tint = crossColor
                )
            }
        }
    }
}