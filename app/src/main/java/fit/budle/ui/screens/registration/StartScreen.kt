package fit.budle.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import fit.budle.R
import fit.budle.event.customer.RegistrationEvent
import fit.budle.navigation.NavRoute
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.RegistrationViewModel

@Composable
fun StartScreen(
    navHostController: NavHostController,
    viewModel: RegistrationViewModel,
) {
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
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(230.dp)
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
                    navHostController.navigate("numberScreen")
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
                            viewModel.onEvent(RegistrationEvent.Login)
                            navHostController.navigate("dataScreen")
                        },
                    color = fillPurple,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}