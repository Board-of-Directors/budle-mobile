package fit.budle.ui.components.atoms.inputs.text_fields

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudleNumberTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: MutableState<String> = mutableStateOf(""),
    onValueChange: (String) -> Unit,
    startMessage: String = "",
    inputLength: Int,
    mask: String,
    placeHolder: String = "+7",
) {
    var text by remember { mutableStateOf(startMessage) }
    val textColor = if (enabled) mainBlack else textGray
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier,
        enabled = enabled,
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { it ->
            text = it.filter { it.isDigit() }
            if (it.isEmpty()) {
                error.value = "Введите номер"
            } else if (it.length < inputLength) {
                error.value = "Введите номер полностью"
            } else if (it.length == inputLength) {
                error.value = ""
                focusManager.clearFocus()
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = backgroundLightBlue,
            unfocusedContainerColor = backgroundLightBlue,
            disabledTextColor = textColor
        ),
        visualTransformation = MaskVisualTransformation(mask),
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.bodyMedium,
                color = textGray
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium
    )
    onValueChange(text)
}
