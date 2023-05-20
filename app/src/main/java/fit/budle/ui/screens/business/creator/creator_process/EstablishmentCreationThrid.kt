package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.dto.events.EstCreationEvent
import fit.budle.ui.components.atoms.inputs.photo_inputs.BudleMultiplePhotoInput
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleMultipleLineInput
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.util.bitmapCreator
import fit.budle.viewmodel.business.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationThirdScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    viewModel.selectedPhotosBitmap = bitmapCreator(
        uris = viewModel.selectedPhotosUri.toTypedArray()
    )

    Surface(Modifier.fillMaxSize()) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            buttonText = "Следующий шаг",
            progress = "60%",
            onClick = {
                viewModel.onEvent(EstCreationEvent.ThirdStep)
                navHostController.navigate("fourthStep")
            },
            textMessage = "Создание заведения"
        ) {
            BudleMultipleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onValueChange = { viewModel.selectedDescription = it },
                symbolsCount = true,
                placeHolderColor = mainBlack,
                placeHolder = "Описание",
                startMessage = viewModel.selectedDescription,
                textFieldMessage = "Опишите ваше заведение",
                description = "Макс. 300 символов",
            )
            BudleMultiplePhotoInput(
                onValueChange = {
                    viewModel.selectedPhotosUri.clear()
                    viewModel.selectedPhotosUri.addAll(it)
                },
                selectedItems = viewModel.selectedPhotosUri
            )
        }
    }
}