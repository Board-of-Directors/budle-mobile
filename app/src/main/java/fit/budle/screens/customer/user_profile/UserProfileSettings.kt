package fit.budle.screens.customer.user_profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.inputs.BudleInputTextField
import fit.budle.components.atoms.inputs.BudleNumberField
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.navigation.routes.NavRoute
import nsu.app.budle.components.BudlePasswordTextField
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainWhite

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
            BudleNavigationHeader(
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
            BudleButton(
                onClick = {},
                buttonText = "Сохранить изменения",
                disabledButtonColor = fillPurple,
                activeButtonColor = fillPurple,
                disabledTextColor = mainWhite,
                activeTextColor = mainWhite
            )
        }
    }
}