package fit.budle.ui.components.atoms.inputs.text_inputs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.components.atoms.inputs.text_fields.BudleNumberTextField
import fit.budle.ui.models.FromDefaults
import fit.budle.ui.models.ToDefaults

@Composable
fun BudleFromToTimeInput(
    modifier: Modifier = Modifier,
    onValueChange: (Pair<String, String>) -> Unit,
    enabled: Boolean = true,
) {

    var fromText by remember { mutableStateOf("") }
    var toText by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BudleNumberTextField(
            modifier = Modifier.width(160.dp),
            enabled = enabled,
            onValueChange = { fromText = it },
            placeHolder = "с 8:00",
            inputLength = FromDefaults.INPUT_LENGTH,
            mask = FromDefaults.MASK
        )
        BudleNumberTextField(
            modifier = Modifier
                .padding(start = 10.dp)
                .width(160.dp),
            enabled = enabled,
            onValueChange = { toText = it },
            placeHolder = "до 22:00",
            inputLength = ToDefaults.INPUT_LENGTH,
            mask = ToDefaults.MASK
        )
    }
    onValueChange(Pair(fromText, toText))
}