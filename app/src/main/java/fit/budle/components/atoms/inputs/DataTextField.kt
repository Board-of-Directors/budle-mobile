package fit.budle.components.atoms.inputs

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
import fit.budle.components.data.DataDefaults.INPUT_LENGTH
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.textGray

@Composable
fun BudleInputTextField(
    modifier: Modifier,
    placeHolder: String,
    placeHolderColor: Color = textGray,
    startMessage: String,
    textFieldMessage: String,
) {
    var textInputState = ""
    val textError = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = placeHolder,
            style = MaterialTheme.typography.bodyMedium,
            color = placeHolderColor,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        textInputState = dataTextField(
            startMessage = startMessage,
            placeholder = textFieldMessage,
            error = textError
        )
        if (textError.value) {
            Text(
                text = "Это поле не может быть пустым",
                style = MaterialTheme.typography.bodyMedium,
                color = backgroundError,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dataTextField(
    startMessage: String,
    placeholder: String,
    error: MutableState<Boolean>
): String {

    var text by remember { mutableStateOf(startMessage) }
    val strokeColor = if (!error.value) Color.Transparent else backgroundError

    Card(
        border = BorderStroke(2.dp, strokeColor)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                if (it.length <= INPUT_LENGTH) {
                    if (it.isNotEmpty()) {
                        if (it[it.length - 1] != '\n') {
                            if (error.value) error.value = false
                            text = it
                        }
                    } else text = it
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = backgroundLightBlue,
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
    return text
}