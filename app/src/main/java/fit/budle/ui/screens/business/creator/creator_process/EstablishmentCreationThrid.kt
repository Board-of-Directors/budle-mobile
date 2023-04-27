package fit.budle.ui.screens.business.creator.creator_process

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fit.budle.ui.components.atoms.inputs.photo_inputs.BudleMultiplePhotoInput
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleMultipleLineInput
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.mainBlack
import fit.budle.viewmodel.EstCreationViewModel

@Composable
fun EstablishmentCreationThirdScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel
) {
    Surface(Modifier.fillMaxSize()) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "60%",
            onClick = {
                navHostController.navigate("fourthStep")
            },
            textMessage = "Создание заведения"
        ) {
            BudleMultipleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                symbolsCount = true,
                placeHolderColor = mainBlack,
                placeHolder = "Описание",
                startMessage = "",
                textFieldMessage = "Опишите ваше заведение",
                description = "Макс. 300 символов",
            )
            BudleMultiplePhotoInput()
        }
    }
}