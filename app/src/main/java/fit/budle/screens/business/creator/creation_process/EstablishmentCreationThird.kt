package fit.budle.screens.business.creator.creation_process

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.inputs.BudleInputTextField
import fit.budle.components.atoms.inputs.photo_picker.BudleMultiplePhotoInput
import fit.budle.components.data.DataDefaults.LONG_INPUT_LENGTH
import fit.budle.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.mainBlack

@Composable
fun EstablishmentCreationThirdScreen(
    navHostController: NavHostController
) {
    BudleScreenWithButtonAndProgress(
        navHostController = navHostController,
        buttonText = "Следующий шаг",
        progress = "60%",
        route = NavRoute.EstablishmentCreationFourth.route,
        textMessage = "Создание заведения"
    ) {
        BudleInputTextField(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            symbolsCount = true,
            placeHolderColor = mainBlack,
            placeHolder = "Описание",
            startMessage = "",
            textFieldMessage = "Опишите ваше заведение",
            description = "Макс. 300 символов",
            textLength = LONG_INPUT_LENGTH
        )
        BudleMultiplePhotoInput()
    }
}