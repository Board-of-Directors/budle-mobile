package nsu.app.budle.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.budle.R
import nsu.app.budle.navigation.NavRoute
import nsu.app.budle.ui.theme.BudleTheme

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
                    .width(200.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Number.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF654DF6)),
            ) {
                Text(
                    text = "Зарегистрироваться",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Row () {
                Text(
                    text = "Уже есть аккаунт? ",
                    modifier = Modifier.padding(top = 20.dp),
                    fontSize = 16.sp,
                    color = Color(0xFFB6C1CE),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Войти",
                    modifier = Modifier.padding(top = 20.dp),
                    fontSize = 16.sp,
                    color = Color(0xFF654DF6),
                    style = MaterialTheme.typography.labelSmall
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevStartScreen() {
    BudleTheme {
        StartScreen(navController = rememberNavController())
    }
}