package nsu.app.budle.screens

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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.screens.DataDefaults.INPUT_LENGTH
import nsu.app.budle.ui.theme.backgroundLightBlue
import nsu.app.budle.ui.theme.fillPurple
import nsu.app.budle.ui.theme.textGray
import kotlin.math.absoluteValue

@Composable
fun DataScreen(navController: NavHostController) {
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
                    onClick = { navController.navigate(route = NavRoute.Code.route) },
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
                DataTextField("Введите Ваше имя")
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "Пароль",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                PasswordTextField("Введите пароль")
                Text(
                    text = "Придумайте пароль от 8 знаков из\n" +
                            "цифр и латинских букв",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.End.route)
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
fun DataTextField(placeholder: String) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
            if (it.length <= INPUT_LENGTH) text = it
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
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(placeholder: String) {

    val focusManager = LocalFocusManager.current
    val showPassword = remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = {
            if (it.length <= INPUT_LENGTH) text = it
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

object DataDefaults {
    const val INPUT_LENGTH = 30
}
