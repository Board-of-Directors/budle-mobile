package fit.budle.ui.screens.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.viewmodel.customer.RegistrationViewModel

@Composable
fun EndScreen(
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
            BudleButton(
                onClick = {
                    navHostController.navigate(route = "main")
                },
                buttonText = "Завершить регистрацию"
            )
        }
    }
}