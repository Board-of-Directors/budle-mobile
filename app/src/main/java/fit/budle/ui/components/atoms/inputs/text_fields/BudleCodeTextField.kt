package fit.budle.ui.components.atoms.inputs.text_fields

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.fillPurple

@Composable
fun BudleCodeTextField(
    states: SnapshotStateList<String>,
    i: Int,
    firstInput: Int,
    lastInput: Int,
    errorState: Boolean,
    focusManager: FocusManager,
) {
    TextField(
        value = states[i],
        modifier = Modifier
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            .width(60.dp)
            .onKeyEvent { event: KeyEvent ->
                if (event.type == KeyEventType.KeyUp &&
                    event.key == Key.Backspace &&
                    states[i].isEmpty()
                ) {
                    if (i == 0) {
                        focusManager.clearFocus()
                    } else {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                }
                false
            },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            if (it.length <= 1) {
                states[i] = it
            }
            if (it.isNotEmpty()) {
                if (i == lastInput) {
                    focusManager.clearFocus()
                } else {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            } else {
                if (i == firstInput) {
                    focusManager.clearFocus()
                } else {
                    focusManager.moveFocus(FocusDirection.Previous)
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = fillPurple,
            unfocusedIndicatorColor = if (!errorState || states[i].isNotEmpty())
                backgroundLightBlue else backgroundError,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.displayLarge
    )
}
