package fit.budle.components.atoms.inputs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.components.data.DataDefaults
import fit.budle.components.data.DataDefaults.LONG_INPUT_LENGTH
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.textGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun budleMultipleTextField(
    startMessage: String,
    placeholder: String,
    error: MutableState<Boolean>,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
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
                if (it.length <= LONG_INPUT_LENGTH) {
                    if (it.isNotEmpty()) {
                        if (it[0] != '\n') {
                            if (backSlashCount < 2) {
                                if (error.value) error.value = false
                                text = it
                                if (it[it.length - 1] == '\n') {
                                    backSlashCount++
                                } else backSlashCount = 0
                                // adding new symbols
                            } else if (it[it.length - 1] != '\n') {
                                text = it
                                backSlashCount = 0
                                // deleting symbols
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
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
    return text
}