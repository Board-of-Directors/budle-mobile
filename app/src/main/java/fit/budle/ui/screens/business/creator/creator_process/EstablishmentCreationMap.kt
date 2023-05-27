package fit.budle.ui.screens.business.creator.creator_process

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fit.budle.event.business.EstCreationEvent
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.atoms.inputs.photo_inputs.BudleSingleSelectPhotoInput
import fit.budle.ui.components.moleculas.screens.BudleScreenWithButtonAndProgress
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainWhite
import fit.budle.viewmodel.business.EstCreationViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EstablishmentCreationMapScreen(
    navHostController: NavHostController,
    viewModel: EstCreationViewModel,
) {

    val filePicker = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { file -> if (file != null) viewModel.selectedMapUri = file }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        BudleScreenWithButtonAndProgress(
            navHostController = navHostController,
            onClick = {
                viewModel.onEvent(EstCreationEvent.CreateMap)
                navHostController.navigate("fifthStep")
            },
            buttonText = "Следующий шаг",
            textMessage = "Создание заведения",
            progress = "20%"
        ) {
            BudleSingleSelectPhotoInput(
                onValueChange = {
                    viewModel.selectedMapUri = it
                },
                initialState = viewModel.selectedMapUri,
                isError = false,
                headerText = "Загрузите карту заведения"
            )
            BudleButton(
                onClick = { filePicker.launch("image/*") },
                buttonText = "Launch picker",
                disabledButtonColor = fillPurple,
                disabledTextColor = mainWhite
            )
        }
    }
}