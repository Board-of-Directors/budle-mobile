package fit.budle.components.atoms.inputs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
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
import fit.budle.components.data.DataDefaults.LONG_INPUT_LENGTH
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.textGray

@Composable
fun BudleInputTextField(
    description: String? = null,
    textLength: Int = INPUT_LENGTH,
    modifier: Modifier,
    placeHolder: String,
    symbolsCount: Boolean = false,
    placeHolderColor: Color = textGray,
    startMessage: String,
    textFieldMessage: String,
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
        textInputState = dataTextField(
            startMessage = startMessage,
            placeholder = textFieldMessage,
            error = textError,
            textLength = textLength
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dataTextField(
    startMessage: String,
    placeholder: String,
    error: MutableState<Boolean>,
    textLength: Int = INPUT_LENGTH
): String {
    
    var backSlashCount by remember { mutableStateOf(0) }
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
                if (it.length <= textLength) {
                    if (it.isNotEmpty()) {
                        if (textLength != LONG_INPUT_LENGTH) {
                            if (it[it.length - 1] != '\n') {
                                if (error.value) error.value = false
                                text = it
                            }
                        } else if (it[0] != '\n') {
                            if (backSlashCount < 2) {
                                if (error.value) error.value = false
                                text = it
                                if (it[it.length - 1] == '\n') {
                                    backSlashCount++
                                } else backSlashCount = 0
                            } else if (it[it.length - 1] != '\n') {
                                text = it
                                backSlashCount = 0
                            } else if (it.substring(it.length - 2, it.length) != "\n\n") {
                                text = it
                                backSlashCount--
                            }
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