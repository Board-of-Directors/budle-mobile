package nsu.app.budle.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.screens.DataDefaults.INPUT_LENGTH
import nsu.app.budle.screens.DataDefaults.PASSWORD_LENGTH
import nsu.app.budle.ui.theme.backgroundError
import nsu.app.budle.ui.theme.backgroundLightBlue
import nsu.app.budle.ui.theme.fillPurple
import nsu.app.budle.ui.theme.textGray
import org.jetbrains.annotations.NotNull

@Composable
fun DataScreen(navController: NavHostController, buttonText: String?) {

    var textInputState = ""
    var passwordInputState = ""
    val passError = remember { mutableStateOf(false) }
    val textError = remember { mutableStateOf(false) }

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
                val navScreen =
                    if (buttonText == "Войти") NavRoute.Start.route
                    else NavRoute.Code.route
                IconButton(
                    onClick = { navController.navigate(route = navScreen) },
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
                Text(
                    text = "Имя пользователя",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                textInputState = dataTextField("Введите Ваше имя", textError)
                if (textError.value){
                    Text(
                        text = "Это поле не может быть пустым",
                        style = MaterialTheme.typography.bodyMedium,
                        color = backgroundError,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 20.dp)
            ) {
                val hintColor = if (!passError.value) textGray else backgroundError
                Text(
                    text = "Пароль",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                passwordInputState = passwordTextField("Введите пароль", passError)
                Text(
                    text = "Придумайте пароль от 8 знаков из\n" +
                            "цифр и латинских букв",
                    style = MaterialTheme.typography.bodyMedium,
                    color = hintColor,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    textError.value = textInputState.isEmpty()
                    passError.value = passwordInputState.length < 8
                    if (!textError.value && !passError.value) {
                        navController.navigate(route = NavRoute.End.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = fillPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(bottom = 90.dp)
            ) {
                buttonText?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dataTextField(placeholder: String, error: MutableState<Boolean>): String {

    var text by remember { mutableStateOf("") }
    val strokeColor = if (!error.value) Color.Transparent else backgroundError

    Card(
        border = BorderStroke(2.dp, strokeColor)
    ) {
        TextField(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun passwordTextField(placeholder: String, error: MutableState<Boolean>): String {

    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }
    val strokeColor = if (!error.value) Color.Transparent else backgroundError

    Card(
        border = BorderStroke(2.dp,strokeColor)
    ){
        TextField(
            value = text,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                if (it.length <= INPUT_LENGTH) {
                    if (it.isNotEmpty()) {
                        if (it[it.length - 1] != '\n') {
                            if (it.length >= PASSWORD_LENGTH){
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
                    text = placeholder, style = MaterialTheme.typography.bodyMedium, color = textGray
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

object DataDefaults {
    const val INPUT_LENGTH = 30
    const val PASSWORD_LENGTH = 8
}
