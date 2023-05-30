package fit.budle.ui.screens.business.creator_process

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.event.business.EstCreationEvent
import fit.budle.ui.components.atoms.inputs.photo_inputs.BudleSingleSelectPhotoInput
import fit.budle.ui.components.atoms.inputs.text_inputs.BudleSingleLineInput
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.mainBlack
import fit.budle.viewmodel.business.EstCreationViewModel


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationFirstScreen(
    navHostController: NavHostController,
    estCreationViewModel: EstCreationViewModel,
) {

    var buttonClicked by remember { mutableStateOf(false) }
    var emptyImageError by remember { mutableStateOf(false) }
    var emptyNameInputError by remember { mutableStateOf(false) }

    val source = if (estCreationViewModel.selectedImageUri != null) {
        ImageDecoder.createSource(
            LocalContext.current.contentResolver,
            estCreationViewModel.selectedImageUri as Uri
        )
    } else null

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            onClick = {
                buttonClicked = true
                if (!emptyNameInputError && !emptyImageError) {
                    estCreationViewModel.onEvent(EstCreationEvent.FirstStep(source))
                    navHostController.navigate("secondStep")
                }
            },
            buttonText = "Следующий шаг",
            textMessage = "Создание заведения",
            progress = "20%"
        ) {
            BudleSingleSelectPhotoInput(
                onValueChange = {
                    estCreationViewModel.selectedImageUri = it
                    emptyImageError = estCreationViewModel.selectedImageUri == null
                },
                initialState = estCreationViewModel.selectedImageUri,
                isError = if (buttonClicked && estCreationViewModel.selectedImageUri == null) {
                    emptyImageError
                } else false
            )
            BudleSingleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onValueChange = {
                    estCreationViewModel.selectedName = it
                    emptyNameInputError = estCreationViewModel.selectedName.isEmpty()
                },
                isError = if (buttonClicked) emptyNameInputError else false,
                placeHolderColor = mainBlack,
                placeHolder = "Название",
                startMessage = estCreationViewModel.selectedName,
                textFieldMessage = "Введите название"
            )
        }
    }
}