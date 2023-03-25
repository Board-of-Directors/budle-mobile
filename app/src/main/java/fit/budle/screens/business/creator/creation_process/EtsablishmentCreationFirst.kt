package fit.budle.screens.business.creator.creation_process

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.inputs.BudleInputTextField
import fit.budle.components.atoms.inputs.BudleSingleSelectPhotoInput
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.*

@Composable
fun EstablishmentCreationFirstScreen(
    navHostController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp)
                .padding(horizontal = 20.dp)
        ) {
            BudleNavigationHeader(
                percent = "20%",
                textMessage = "Создание заведения",
                onClick = {
                    navHostController.popBackStack()
                }
            )
            FirstEstablishmentCreation(
                navHostController = navHostController,
                progress = "20%"
            )
        }
    }
}

@Composable
fun FirstEstablishmentCreation(
    navHostController: NavHostController,
    progress: String
) {
    BudleSingleSelectPhotoInput()
    BudleInputTextField(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        placeHolderColor = mainBlack,
        placeHolder = "Название",
        startMessage = "",
        textFieldMessage = "Введите название"
    )
    BudleButton(
        onClick = {
            navHostController.navigate(NavRoute.EstablishmentCreationSecond.route)
        },
        horizontalPadding = 0.dp,
        buttonText = "Следующий шаг",
        disabledButtonColor = fillPurple,
        disabledTextColor = mainWhite,
    )
}