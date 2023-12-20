package fit.budle.ui.components.atoms.inputs.text_inputs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fit.budle.ui.components.atoms.inputs.text_fields.BudleNumberTextField
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.textGray

@Composable
fun BudleNumberInput(
    modifier: Modifier = Modifier,
    placeHolder: String? = null,
    startMessage: String = "",
    inputLength: Int,
    mask: String,
    onValueChange: (String) -> Unit,
    exceptionMessage: String,
) {
    val inputError = remember { mutableStateOf("") }
    inputError.value = exceptionMessage
    var numberState by remember { mutableStateOf("") }
    val stateColor = if (inputError.value.isEmpty()) Color.Transparent else backgroundError

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        if (placeHolder != null) {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium,
                color = textGray,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        Card(border = BorderStroke(2.dp, stateColor)) {
            BudleNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                error = inputError,
                onValueChange = { numberState = it },
                startMessage = startMessage,
                inputLength = inputLength,
                mask = mask
            )
        }
        if (inputError.value.isNotEmpty()) {
            Text(
                text = inputError.value,
                style = MaterialTheme.typography.bodyMedium,
                color = backgroundError,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        onValueChange("7${numberState}")
    }
}
