package fit.budle.ui.screens.business.creator.creator_process

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
    viewModel: EstCreationViewModel,
) {

    var buttonClicked by remember { mutableStateOf(false) }
    var emptyImageError by remember { mutableStateOf(false) }
    var emptyNameInputError by remember { mutableStateOf(false) }

    val source = if (viewModel.selectedImageUri != null) {
        ImageDecoder.createSource(
            LocalContext.current.contentResolver,
            viewModel.selectedImageUri as Uri
        )
    } else null

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            onClick = {
                if (!emptyNameInputError && !emptyImageError) {
                    buttonClicked = true
                    viewModel.onEvent(EstCreationEvent.FirstStep(source))
                    navHostController.navigate("secondStep")
                }
            },
            buttonText = "Следующий шаг",
            textMessage = "Создание заведения",
            progress = "20%"
        ) {
            BudleSingleSelectPhotoInput(
                onValueChange = {
                    viewModel.selectedImageUri = it
                    emptyImageError = viewModel.selectedImageUri == null
                },
                initialState = viewModel.selectedImageUri,
                isError = if (buttonClicked && viewModel.selectedImageUri == null) {
                    emptyImageError
                } else false
            )
            BudleSingleLineInput(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                onValueChange = {
                    viewModel.selectedName = it
                    emptyNameInputError = viewModel.selectedName.isEmpty()
                },
                isError = if (buttonClicked) emptyNameInputError else false,
                placeHolderColor = mainBlack,
                placeHolder = "Название",
                startMessage = viewModel.selectedName,
                textFieldMessage = "Введите название"
            )
        }
    }
}