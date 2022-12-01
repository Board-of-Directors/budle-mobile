package nsu.app.budle.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.ui.theme.fillPurple
import nsu.app.budle.ui.theme.textGray

@Composable
fun StartScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.start_picture),
                contentDescription = "Start Picture",
                modifier = Modifier
                    .width(323.dp)
                    .padding(bottom = 60.dp)
            )
            Image(
                painter = painterResource(
                    id = R.drawable.logo_big
                ),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(148.dp)
                    .padding(bottom = 21.dp)
            )
            Text(
                text = "Бронируйте места в пару кликов!",
                modifier = Modifier
                    .padding(bottom = 51.dp)
                    .width(180.dp),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Number.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = fillPurple),

                ) {
                Text(
                    text = "Зарегистрироваться",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row {
                Text(
                    text = "Уже есть аккаунт? ",
                    modifier = Modifier.padding(top = 20.dp),
                    color = textGray,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Войти",
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .clickable {
                            navController.navigate("data_screen/Войти")
                        },
                    color = fillPurple,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
