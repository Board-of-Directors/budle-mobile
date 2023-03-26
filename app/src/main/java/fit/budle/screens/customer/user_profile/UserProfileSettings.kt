package fit.budle.screens.customer.user_profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.inputs.text_inputs.BudleNumberInput
import fit.budle.components.atoms.inputs.text_inputs.BudleSingleLineInput
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainWhite
import nsu.app.budle.components.BudlePasswordInput

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
                onClick = {
                    navController.popBackStack()
                }
            )
            BudleSingleLineInput(
                modifier = screenModifier,
                placeHolder = "ФИО",
                startMessage = "Третьяков Артём",
                textFieldMessage = "Введите ваше имя"
            )
            BudleNumberInput(
                modifier = screenModifier,
                placeHolder = "Телефон",
                startMessage = "9139391194"
            )
            BudlePasswordInput(
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