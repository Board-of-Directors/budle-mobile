package fit.budle.ui.components.atoms.inputs.text_inputs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.components.atoms.inputs.text_fields.budleNumberTextField
import fit.budle.ui.models.FromDefaults
import fit.budle.ui.models.ToDefaults

@Composable
fun BudleFromToTimeInput(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        budleNumberTextField(
            modifier = Modifier.width(160.dp),
            enabled = enabled,
            placeHolder = "с 8:00",
            inputLength = FromDefaults.INPUT_LENGTH,
            mask = FromDefaults.MASK
        )
        budleNumberTextField(
            modifier = Modifier.padding(start = 10.dp).width(160.dp),
            enabled = enabled,
            placeHolder = "до 22:00",
            inputLength = ToDefaults.INPUT_LENGTH,
            mask = ToDefaults.MASK
        )
    }
}