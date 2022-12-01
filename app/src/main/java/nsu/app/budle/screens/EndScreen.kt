package nsu.app.budle.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
fun EndScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.end_screen_icon),
                contentDescription = "End Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(210.dp)
                    .padding(bottom = 60.dp)
            )
            Image(
                painter = painterResource(
                    id = R.drawable.logo_big
                ),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(180.dp)
                    .padding(bottom = 21.dp)
            )
            Text(
                text = "Регистрация прошла успешно!\nПриступаем к бронированию",
                modifier = Modifier
                    .padding(bottom = 51.dp)
                    .width(330.dp),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Start.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = fillPurple),

                ) {
                Text(
                    text = "Завершить регистрацию",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 40.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
