package fit.budle.screens.business.creator.creation_process

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.inputs.text_inputs.BudleSingleLineInput
import fit.budle.components.atoms.inputs.photo_picker.BudleSingleSelectPhotoInput
import fit.budle.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.*

@Composable
fun EstablishmentCreationFirstScreen(
    navHostController: NavHostController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            route = NavRoute.EstablishmentCreationSecond.route,
            buttonText = "Следующий шаг",
            textMessage = "Создание заведения",
            progress = "20%"
        ) {
            BudleSingleSelectPhotoInput()
            BudleSingleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                placeHolderColor = mainBlack,
                placeHolder = "Название",
                startMessage = "",
                textFieldMessage = "Введите название"
            )
        }
    }
}