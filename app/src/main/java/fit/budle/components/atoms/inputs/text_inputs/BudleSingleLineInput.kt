package fit.budle.components.atoms.inputs.text_inputs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.inputs.text_fields.budleSingleLineTextField
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.textGray

@Composable
fun BudleSingleLineInput(
    modifier: Modifier = Modifier,
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
    val textError = remember { mutableStateOf(false) }

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
            if (placeHolder != null){
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = placeHolderColor,
                )
            }
        }
        textInputState = budleSingleLineTextField(
            startMessage = startMessage,
            placeholder = textFieldMessage,
            error = textError,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
        if (textError.value) {
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