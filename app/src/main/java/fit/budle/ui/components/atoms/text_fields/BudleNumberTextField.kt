package fit.budle.ui.components.atoms.text_fields

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun budleNumberTextField(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    error: MutableState<Boolean> = mutableStateOf(false),
    startMessage: String = "",
    inputLength: Int,
    mask: String,
    placeHolder: String = "+7",
): String {

    var text by remember { mutableStateOf(startMessage) }
    val textColor = if (enabled) mainBlack else textGray

    TextField(
        modifier = modifier,
        enabled = enabled,
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { it ->
            if (it.length <= inputLength) {
                if (error.value && it.length == inputLength)
                    error.value = false
                text = it.filter { it.isDigit() }
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            containerColor = backgroundLightBlue,
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
    return text
}