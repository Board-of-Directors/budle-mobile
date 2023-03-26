package fit.budle.components.atoms.inputs.text_inputs

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import fit.budle.components.data.NumberDefaults.INPUT_LENGTH
import fit.budle.components.data.NumberDefaults.MASK
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.textGray
import kotlin.math.absoluteValue

@Composable
fun BudleNumberInput(
    modifier: Modifier,
    placeHolder: String,
    startMessage: String,
) {
    val error = remember { mutableStateOf(false) }
    var numberState by remember { mutableStateOf("") }
    val stateColor = if (!error.value) Color.Transparent else backgroundError
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(
            text = placeHolder,
            style = MaterialTheme.typography.bodyMedium,
            color = textGray,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Card(border = BorderStroke(2.dp, stateColor)) {
            numberState = simpleTextField(error, startMessage)
        }
        if (error.value) {
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
fun simpleTextField(
    error: MutableState<Boolean>,
    startMessage: String
): String {
    var text by remember { mutableStateOf(startMessage) }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { it ->
            if (it.length <= INPUT_LENGTH) {
                if (error.value && it.length == INPUT_LENGTH)
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
        ),
        visualTransformation = MaskVisualTransformation(MASK),
        placeholder = {
            Text(
                text = "+7", style = MaterialTheme.typography.bodyMedium, color = textGray
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium
    )
    return text
}

class MaskVisualTransformation(private val mask: String) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}