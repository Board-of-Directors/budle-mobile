package fit.budle.ui.components.atoms.inputs.text_fields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fit.budle.ui.models.NumberDefaults.INPUT_LENGTH
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.textGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudleSingleLineTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    inputLength: Int = INPUT_LENGTH,
    isTimeInput: Boolean = false,
    predicate: (String) -> Boolean = { true },
    startMessage: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    error: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    var text by remember { mutableStateOf(startMessage) }
    val strokeColor = if (!error) Color.Transparent else backgroundError

    Card(
        modifier = modifier,
        border = BorderStroke(2.dp, strokeColor)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (isTimeInput && !it.isFocused) {

                        val charArray = text.toCharArray()

                        if (text.length > 1 && text[1] == ':') {
                            text = "0$text"
                        } else if (text.length > 3) {
                            if (text[3] == ':'){
                                val prevChar = charArray[3]
                                charArray[3] = charArray[2]
                                charArray[2] = prevChar
                            }
                            else if (text[4] == ':'){
                                val prevChar = charArray[2]
                                charArray[2] = charArray[4]
                                charArray[4] = prevChar
                            }
                            text = String(charArray)
                        }
                    }
                },
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                if (it.length <= inputLength && predicate(it)) {
                    if (it.isNotEmpty()) {
                        if (it[it.length - 1] != '\n') {
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
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = MaterialTheme.typography.bodyMedium,
            enabled = enabled
        )
    }
    onValueChange(text)
}