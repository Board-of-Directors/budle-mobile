package fit.budle.ui.components.atoms.inputs.text_inputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import fit.budle.ui.components.atoms.inputs.text_fields.BudleMultipleTextField
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.textGray

@Composable
fun BudleMultipleLineInput(
    description: String? = null,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeHolder: String,
    placeHolderColor: Color = textGray,
    startMessage: String,
    textFieldMessage: String,
    symbolsCount: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean,
) {
    var textInputState by remember { mutableStateOf("") }

    val error = remember { mutableStateOf(isError) }


    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium,
                color = placeHolderColor,
            )
            if (symbolsCount) {
                Text(
                    text = "${textInputState.length} / 300 символов",
                    style = MaterialTheme.typography.displaySmall,
                    color = textGray,
                )
            }
        }
        BudleMultipleTextField(
            startMessage = startMessage,
            placeholder = textFieldMessage,
            error = error,
            onValueChange = { textInputState = it },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
        onValueChange(textInputState)
        if (error.value) {
            Text(
                text = "Это поле не может быть пустым",
                style = MaterialTheme.typography.bodyMedium,
                color = backgroundError,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        if (description != null) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = textGray,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}