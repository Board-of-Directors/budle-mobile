package fit.budle.screens.user_profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.moleculas.BudleInputTextField
import nsu.app.budle.components.BudlePasswordTextField
import fit.budle.components.moleculas.BudleNumberField
import fit.budle.components.moleculas.ShowNavigationHeader
import fit.budle.navigation.NavRoute

@Composable
fun UserProfileSettingsScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            val screenModifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 20.dp)
            ShowNavigationHeader(
                textMessage = "Настройки",
                route = NavRoute.UserProfile.route,
                navController = navController
            )
            BudleInputTextField(
                modifier = screenModifier,
                placeHolder = "ФИО",
                startMessage = "Третьяков Артём",
                textFieldMessage = "Введите ваше имя"
            )
            BudleNumberField(
                modifier = screenModifier,
                placeHolder = "Телефон",
                startMessage = "9139391194"
            )
            BudlePasswordTextField(
                modifier = screenModifier,
                placeHolder = "Пароль",
                startMessage = "Feodaloff2003",
                textFieldMessage = "Введите пароль"
            )
        }
    }
}