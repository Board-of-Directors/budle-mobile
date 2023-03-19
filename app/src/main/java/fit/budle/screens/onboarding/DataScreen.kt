package fit.budle.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.inputs.dataTextField
import fit.budle.navigation.NavRoute
import fit.budle.ui.theme.backgroundError
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray
import nsu.app.budle.components.passwordTextField

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
                textInputState = dataTextField("","Введите Ваше имя", textError)
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
                passwordInputState = passwordTextField("","Введите пароль", passError)
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