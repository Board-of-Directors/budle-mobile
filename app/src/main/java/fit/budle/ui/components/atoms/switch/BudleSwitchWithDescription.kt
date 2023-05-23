package fit.budle.ui.components.atoms.switch

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.mainBlack

@Composable
fun BudleSwitchWithDescription(
    modifier: Modifier = Modifier,
    onSwitch: (Boolean) -> Unit,
    text: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BudleSwitch(onSwitch = onSwitch)
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = mainBlack
        )
    }
}