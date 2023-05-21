package fit.budle.ui.components.atoms.inputs.text_inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.components.atoms.inputs.text_fields.BudlePasswordTextField
import fit.budle.ui.theme.textGray

@Composable
fun BudlePasswordInput(
    modifier: Modifier,
    placeHolder: String,
    startMessage: String,
    textFieldMessage: String,
) {
    var passwordInputState = startMessage
    val passError = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = placeHolder,
            style = MaterialTheme.typography.bodyMedium,
            color = textGray,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        BudlePasswordTextField(
            startMessage = startMessage,
            placeholder = textFieldMessage,
            error = passError,
            onValueChange = { passwordInputState = it }
        )
    }
}