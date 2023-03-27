package fit.budle.components.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainWhite

@Composable
fun budleSwitch(): Boolean {

    var isChecked by remember { mutableStateOf(false) }
    val switchColor = if (isChecked) fillPurple else lightBlue
    val arrangement = if (!isChecked) Arrangement.Start else Arrangement.End

    Card(
        modifier = Modifier
            .width(60.dp)
            .height(35.dp)
            .clickable(
                onClick = {
                    isChecked = !isChecked
                }
            ),
        shape = RoundedCornerShape(50f),
        colors = CardDefaults.cardColors(switchColor)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = arrangement
        ) {
            Card(
                shape = CircleShape,
                colors = CardDefaults.cardColors(mainWhite)
            ) {
                Box(modifier = Modifier.size(24.dp))
            }
        }
    }
    return isChecked
}