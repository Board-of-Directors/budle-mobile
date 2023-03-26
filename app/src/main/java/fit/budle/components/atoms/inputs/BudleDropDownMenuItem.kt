package fit.budle.components.atoms.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack

@Composable
fun BudleDropDownMenuItem(
    modifier: Modifier = Modifier,
    item: String,
    isSelected: (String) -> (Boolean),
    onSelect: (String) -> (Unit)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onSelect(item)
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item,
            style = MaterialTheme.typography.bodyMedium,
            color = mainBlack,
        )
        if (isSelected(item)) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "Check",
                tint = fillPurple
            )
        }
    }
}