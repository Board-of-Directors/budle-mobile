package fit.budle.ui.components.atoms.inputs.text_inputs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import fit.budle.ui.components.atoms.inputs.text_fields.BudleSingleLineTextField
import fit.budle.ui.models.TimeDefaults

@Composable
fun BudleFromToTimeInput(
    modifier: Modifier = Modifier,
    fromInitialState: String,
    toInitialState: String,
    onValueChange: (Pair<String, String>) -> Unit,
    enabled: Boolean = true,
) {

    val predicate: (String) -> Boolean = { it ->
        it.isDigitsOnly() || it.filter { char -> char == ':' }.length == 1
    }
    var fromText by remember { mutableStateOf("") }
    var toText by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BudleSingleLineTextField(
            modifier = Modifier.width(160.dp),
            enabled = enabled,
            inputLength = TimeDefaults.INPUT_LENGTH,
            isTimeInput = true,
            startMessage = fromInitialState,
            placeholder = "с 8:00",
            onValueChange = {
                if (predicate(it)) {
                    fromText = it
                }
            },
            predicate = predicate
        )
        BudleSingleLineTextField(
            modifier = Modifier
                .padding(start = 10.dp)
                .width(160.dp),
            enabled = enabled,
            inputLength = TimeDefaults.INPUT_LENGTH,
            isTimeInput = true,
            startMessage = toInitialState,
            placeholder = "до 22:00",
            onValueChange = {
                if (predicate(it)) {
                    toText = it
                }
            },
            predicate = predicate
        )
    }
    onValueChange(Pair(fromText, toText))
}