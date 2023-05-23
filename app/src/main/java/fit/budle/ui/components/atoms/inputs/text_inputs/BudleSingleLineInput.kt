package fit.budle.ui.components.atoms.inputs.text_inputs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fit.budle.ui.components.atoms.inputs.text_fields.BudleSingleLineTextField
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.textGray

@Composable
fun BudleSingleLineInput(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    description: String? = null,
    placeHolder: String? = null,
    placeHolderColor: Color = textGray,
    startMessage: String,
    textFieldMessage: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    var textInputState by remember {
        mutableStateOf("")
    }

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
            if (placeHolder != null) {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = placeHolderColor,
                )
            }
        }
        BudleSingleLineTextField(
            startMessage = startMessage,
            placeholder = textFieldMessage,
            onValueChange = { textInputState = it },
            error = isError,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
        onValueChange(textInputState)
        if (isError) {
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