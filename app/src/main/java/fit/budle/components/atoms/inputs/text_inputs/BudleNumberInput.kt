package fit.budle.components.atoms.inputs.text_inputs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.inputs.text_fields.MaskVisualTransformation
import fit.budle.components.atoms.inputs.text_fields.budleNumberTextField
import fit.budle.components.data.NumberDefaults
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.textGray

@Composable
fun BudleNumberInput(
    modifier: Modifier = Modifier,
    placeHolder: String? = null,
    startMessage: String = "",
    inputLength: Int,
    mask: String
) {
    val error = remember { mutableStateOf(false) }
    var numberState by remember { mutableStateOf("") }
    val stateColor = if (!error.value) Color.Transparent else backgroundError
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        if (placeHolder != null){
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium,
                color = textGray,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        Card(border = BorderStroke(2.dp, stateColor)) {
            numberState = budleNumberTextField(
                error = error,
                startMessage = startMessage,
                inputLength = inputLength,
                mask = mask
            )
        }
        if (error.value) {
            Text(
                text = "Это поле не может быть пустым",
                style = MaterialTheme.typography.bodyMedium,
                color = backgroundError,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}