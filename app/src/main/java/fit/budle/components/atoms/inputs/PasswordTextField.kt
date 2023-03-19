package nsu.app.budle.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.components.data.DataDefaults.INPUT_LENGTH
import fit.budle.components.data.DataDefaults.PASSWORD_LENGTH
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.backgroundLightBlue
import fit.budle.ui.theme.textGray

@Composable
fun BudlePasswordTextField(
    modifier: Modifier,
    placeHolder: String,
    startMessage: String,
    textFieldMessage: String,
) {

    var passwordInputState = startMessage
    val passError = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        val hintColor = if (!passError.value) textGray else backgroundError
        Text(
            text = placeHolder,
            style = MaterialTheme.typography.bodyMedium,
            color = textGray,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        passwordInputState = passwordTextField(startMessage,textFieldMessage, passError)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun passwordTextField(
    startMessage: String,
    placeholder: String,
    error: MutableState<Boolean>
): String {

    var text by remember { mutableStateOf(startMessage) }
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }
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
                            if (it.length >= PASSWORD_LENGTH) {
                                error.value = false
                            }
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
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            trailingIcon = {

                if (showPassword.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye_off),
                        contentDescription = "Password Eye Off Icon",
                        tint = textGray
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.eye),
                        contentDescription = "Password Eye Off Icon",
                        tint = textGray
                    )
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye),
                        contentDescription = "Password Eye Icon",
                        tint = textGray
                    )
                }
            },
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
    return text
}
