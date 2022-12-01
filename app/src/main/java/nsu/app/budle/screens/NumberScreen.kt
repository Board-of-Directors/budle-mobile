package nsu.app.budle.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.screens.NumberDefaults.INPUT_LENGTH
import nsu.app.budle.screens.NumberDefaults.MASK
import nsu.app.budle.ui.theme.backgroundError
import nsu.app.budle.ui.theme.backgroundLightBlue
import nsu.app.budle.ui.theme.fillPurple
import nsu.app.budle.ui.theme.textGray
import kotlin.math.absoluteValue

@Composable
fun NumberScreen(navController: NavHostController) {

    val error = remember { mutableStateOf(false) }
    var numberState by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 64.dp)
            ) {
                IconButton(
                    onClick = { navController.navigate(route = NavRoute.Start.route) },
                    modifier = Modifier.padding(end = 40.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left_gray),
                        contentDescription = "Arrow Left",
                        tint = textGray
                    )
                }
                Image(
                    painter = painterResource(
                        id = R.drawable.logo_big
                    ), contentDescription = "Logo", modifier = Modifier.width(148.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 60.dp)
            ) {
                val stateColor = if (!error.value) Color.Transparent else backgroundError
                Text(
                    text = "Номер телефона",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Card(border = BorderStroke(2.dp, stateColor)) {
                    numberState = simpleTextField(error)
                }
                if(error.value){
                    Text(
                        text = "Это поле не может быть пустым",
                        style = MaterialTheme.typography.bodyMedium,
                        color = backgroundError,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    error.value = numberState.length != 10
                    if (!error.value)
                        navController.navigate(route = NavRoute.Code.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = fillPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 90.dp)
            ) {
                Text(
                    text = "Подтвердить",
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun simpleTextField(error: MutableState<Boolean>): String {
    var text by remember { mutableStateOf("") }
    TextField(
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

object NumberDefaults {
    const val MASK = "+7 (###) ###-##-##"
    const val INPUT_LENGTH = 10
}
